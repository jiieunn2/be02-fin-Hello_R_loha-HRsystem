package com.HelloRolha.HR.feature.approve.service;

import com.HelloRolha.HR.feature.approve.model.Approve;
import com.HelloRolha.HR.feature.approve.model.ApproveLine;
import com.HelloRolha.HR.feature.approve.model.dto.*;
import com.HelloRolha.HR.feature.approve.repo.ApproveFileRepository;
import com.HelloRolha.HR.feature.approve.repo.ApproveLineRepository;
import com.HelloRolha.HR.feature.approve.repo.ApproveRepository;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.employee.repo.EmployeeRepository;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ApproveService {
    private final ApproveRepository approveRepository;
    private final ApproveLineRepository approveLineRepository;
    private final ApproveFileRepository approveFileRepository;
    private final EmployeeRepository employeeRepository;
    private final AmazonS3 s3;

    @Value("gurigiri-s3")
    private String bucket;

    public ApproveCreateRes create(ApproveCreateReq approveCreateReq) {
        Approve approve = Approve.builder()
                .content(approveCreateReq.getContent())
                .title(approveCreateReq.getTitle())
                .build();

        Approve app = approveRepository.save(approve);

        return ApproveCreateRes.builder()
                .id(app.getId())
                .content(app.getContent())
                .status(app.getStatus())
                .title(app.getTitle())
                .build();
    }

    @Transactional
    public ApproveListRes list() {
        List<Approve> approves = approveRepository.findAll();
        List<ApproveList> approveLists = new ArrayList<>();

        for (Approve approve : approves) {
            Employee employee = approve.getEmployee();
            if (employee == null) {
                throw new RuntimeException("Employee 정보를 찾을 수 없습니다.");
            }
            // ApproveList 객체를 생성하고 필요한 정보를 설정합니다.
            ApproveList approveList = new ApproveList();
            approveList.setId(approve.getId());
            approveList.setTitle(approve.getTitle());
            approveList.setStatus(approve.getStatus());
            approveList.setCreateTime(approve.getCreateAt());
            // 여기에서 생성된 ApproveList 객체를 리스트에 추가합니다.
            approveLists.add(approveList);
        }

        // 최종적으로 생성된 approveLists를 포함하는 ApproveListRes 객체를 반환합니다.
        return ApproveListRes.builder()
                .code(1200)
                .message("결재 목록 조회 성공")
                .success(true)
                .isSuccess(true)
                .result(approveLists)
                .build();
    }

    public ApproveReadRes read(Integer approveId) {
        Approve approve = approveRepository.findById(approveId)
                .orElseThrow(() -> new RuntimeException("결재 정보를 찾을 수 없습니다."));

        return ApproveReadRes.builder()
                .id(approve.getId())
                .title(approve.getTitle())
                .status(approve.getStatus())
                // 추가 필드에 대한 설정
                .build();
    }


    @Transactional
    public ApproveUpdateRes update(Integer approveId, ApproveUpdate approveUpdate) {
        Approve approve = approveRepository.findById(approveId)
                .orElseThrow(() -> new RuntimeException("결재 정보를 찾을 수 없습니다."));

        approve.setTitle(approveUpdate.getTitle());
        approve.setContent(approveUpdate.getContent());
        // 필요한 필드에 대한 추가 업데이트 로직
        approveRepository.save(approve);

        return ApproveUpdateRes.builder()
                .title(approve.getTitle())
                .content(approve.getContent())
                // 추가 필드에 대한 설정
                .build();
    }

    @Transactional
    public void delete(Integer approveId) {
        Approve approve = approveRepository.findById(approveId)
                .orElseThrow(() -> new RuntimeException("결재 정보를 찾을 수 없습니다."));

        approveRepository.delete(approve);
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
        try (InputStream input = file.getInputStream()) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            s3.putObject(bucket, saveFileName.replace(File.separator, "/"), input, metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        return s3.getUrl(bucket, saveFileName.replace(File.separator, "/")).toString();
    }
}
