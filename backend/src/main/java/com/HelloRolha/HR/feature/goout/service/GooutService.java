package com.HelloRolha.HR.feature.goout.service;


import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.employee.repo.EmployeeRepository;
import com.HelloRolha.HR.feature.goout.model.Goout;
import com.HelloRolha.HR.feature.goout.model.GooutFile;
import com.HelloRolha.HR.feature.goout.model.entity.*;
import com.HelloRolha.HR.feature.goout.repo.GooutFileRepository;
import com.HelloRolha.HR.feature.goout.repo.GooutRepository;
import com.HelloRolha.HR.feature.goout.repo.GooutTypeRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GooutService {
    private final GooutRepository gooutRepository;
    private final GooutFileRepository gooutFileRepository;
    private final GooutTypeRepository gooutTypeRepository;
    private final EmployeeRepository employeeRepository;
    private final AmazonS3 s3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public Goout create(GooutCreateReq gooutCreateReq) {
        if (gooutCreateReq.getAgentId().equals(gooutCreateReq.getEmployeeId())) {
            throw new IllegalArgumentException("대리자의 ID와 신청직원의 ID는 같을 수 없습니다.");
        }

        Employee agent = employeeRepository.findById(gooutCreateReq.getAgentId())
                .orElseThrow(() -> new IllegalArgumentException("대리자의 ID가 존재하지 않습니다."));
        Employee employee = employeeRepository.findById(gooutCreateReq.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("신청직원의 ID가 존재하지 않습니다."));

        Goout goout = Goout.builder()
                .period(gooutCreateReq.getPeriod())
                .agent(agent)
                .employee(employee)
                .type(gooutCreateReq.getType())
                .first(gooutCreateReq.getFirst())
                .last(gooutCreateReq.getLast())
                .build();

        return gooutRepository.save(goout);
    }

//    이거는 Employee가 null있으면 아예 list가 안나오는 방식
@Transactional
public List<GooutList> list() {
    List<Goout> goouts = gooutRepository.findAll();
    List<GooutList> gooutLists = new ArrayList<>();

    for (Goout goout : goouts) {
        Employee employee = goout.getEmployee();
        if (employee != null) {
            GooutList gooutList = GooutList.builder()
                    .id(goout.getId())
                    .name(employee.getName())
                    .type(goout.getType())
                    .first(goout.getFirst())
                    .last(goout.getLast())
                    .period(goout.getPeriod())
                    .build();
            gooutLists.add(gooutList);
        }
    }

    return gooutLists;
}
//    이거는 Employee가 null이 아닐 경우에만 리스트에 추가하는 방식
//@Transactional
//    public List<GooutList> list() {
//        List<Goout> goouts = gooutRepository.findAll();
//        List<GooutList> gooutLists = new ArrayList<>();
//
//        for (Goout goout : goouts) {
//            Employee employee = goout.getEmployee();
//            if (employee != null) {
//                GooutList gooutList = GooutList.builder()
//                        .id(goout.getId())
//                        .name(employee.getName())
//                        .type(goout.getType())
//                        .first(goout.getFirst())
//                        .last(goout.getLast())
//                        .period(goout.getPeriod())
//                        .build();
//                gooutLists.add(gooutList);
//            }
//        }
//
//        return gooutLists;
//    }


    @Transactional
    public GooutRead read(Integer id) {
        Optional<Goout> optionalGoout = gooutRepository.findById(id);

        return optionalGoout.map(goout -> {
            List<GooutFile> gooutFiles = goout.getGooutFiles();
            String filenames = gooutFiles.stream()
                    .map(GooutFile::getFilename)
                    .collect(Collectors.joining(","));

            Employee employee = goout.getEmployee();
            if (employee == null) {
                throw new RuntimeException("Employee 정보를 찾을 수 없습니다.");
            }

            Employee agent = goout.getAgent();
            if (agent == null) {
                throw new RuntimeException("Agent 정보를 찾을 수 없습니다.");
            }

            return GooutRead.builder()
                    .period(goout.getPeriod())
                    .agentName(agent.getName())
                    .employeeName(employee.getName())
                    .remainingVacationDays(employee.getRemainingVacationDays())
                    .position(employee.getPosition())
                    .type(goout.getType())
                    .first(goout.getFirst())
                    .last(goout.getLast())
                    .filename(filenames)
                    .build();
        }).orElse(null);
    }

    @Transactional
    public void returnStatus(Integer id, Integer status) {
        Goout goout = gooutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 휴가/외출 정보를 찾을 수 없습니다."));
        goout.setStatus(status);
        gooutRepository.save(goout);
    }

    @Transactional
    public void update(GooutUpdateReq gooutUpdateReq) {
        Goout goout = gooutRepository.findById(gooutUpdateReq.getId())
                .orElseThrow(() -> new RuntimeException("해당 ID의 휴가/외출 정보를 찾을 수 없습니다."));

        if (goout.getStatus() != 2) {
            throw new IllegalStateException("반려된 상태의 휴가/외출 정보만 수정할 수 있습니다.");
        }

        // 휴가/외출 정보 업데이트
        goout.setPeriod(gooutUpdateReq.getPeriod());
        goout.setFirst(gooutUpdateReq.getFirst());
        goout.setLast(gooutUpdateReq.getLast());
        gooutRepository.save(goout);

        // 첨부파일 추가
        if (gooutUpdateReq.getNewFiles() != null) {
            for (MultipartFile file : gooutUpdateReq.getNewFiles()) {
                String uploadPath = uploadFile(file);
                GooutFile gooutFile = new GooutFile();
                gooutFile.setFilename(uploadPath);
                gooutFile.setGoout(goout);
                gooutFileRepository.save(gooutFile);
            }
        }

        // 첨부파일 삭제
        if (gooutUpdateReq.getDeleteFileIds() != null) {
            for (Integer fileId : gooutUpdateReq.getDeleteFileIds()) {
                gooutFileRepository.deleteById(fileId);
            }
        }
    }

    @Transactional
    public void delete(Integer id) {
        Goout goout = gooutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 휴가/외출 정보를 찾을 수 없습니다."));

        gooutFileRepository.deleteAllByGoout(goout);

        gooutRepository.delete(goout);
    }

    public String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);

        return folderPath;
    }


    public String uploadFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String saveFileName = folderPath + File.separator + uuid + "_" + originalName;
        InputStream input = null;
        try {
            input = file.getInputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());


            s3.putObject(bucket, saveFileName.replace(File.separator, "/"), input, metadata);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return s3.getUrl(bucket, saveFileName.replace(File.separator, "/")).toString();
    }

    public void saveFile(Integer id, String uploadPath) {
        gooutFileRepository.save(GooutFile.builder()
                .goout(Goout.builder().id(id).build())
                .filename(uploadPath)
                .build());
    }

}
