package com.HelloRolha.HR.feature.approve.service;

import com.HelloRolha.HR.feature.approve.model.Approve;
import com.HelloRolha.HR.feature.approve.model.ApproveFile;
import com.HelloRolha.HR.feature.approve.model.ApproveLine;
import com.HelloRolha.HR.feature.approve.model.dto.Approve.*;
import com.HelloRolha.HR.feature.approve.repo.ApproveFileRepository;
import com.HelloRolha.HR.feature.approve.repo.ApproveLineRepository;
import com.HelloRolha.HR.feature.approve.repo.ApproveRepository;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.employee.repo.EmployeeRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Transactional
    public ApproveCreateRes create(ApproveCreateReq approveCreateReq) {
        Employee employee = ((Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Approve approve = Approve.builder()
                .content(approveCreateReq.getContent())
                .title(approveCreateReq.getTitle())
                .status(0)
                .employee(employee)
                .build();

        Approve app = approveRepository.save(approve);

        return ApproveCreateRes.builder()
                .id(app.getId())
                .employeeId(app.getEmployee().getId())
                .title(app.getTitle())
                .content(app.getContent())
                .status(app.getStatus())
                .createAt(approve.getCreateAt())
                .build();
    }

    @Transactional
    public List<ApproveList> list() {
        List<Approve> approves = approveRepository.findAll();
        List<ApproveList> approveLists = new ArrayList<>();

        for (Approve approve : approves) {
            Employee employee = approve.getEmployee();
            String employeeName = employee != null ? employee.getName() : "Unknown"; // Employee가 null이면 "Unknown"

            String confirmer1Name = "Unknown"; // 초기값 설정
            String confirmer2Name = "Unknown"; // 초기값 설정

            if (!approve.getApproveLines().isEmpty()) {
                ApproveLine firstApproveLine = approve.getApproveLines().get(0); // 첫 번째 ApproveLine 가져오기
                confirmer1Name = firstApproveLine.getConfirmer1() != null ? firstApproveLine.getConfirmer1().getName() : "N/A";
                confirmer2Name = firstApproveLine.getConfirmer2() != null ? firstApproveLine.getConfirmer2().getName() : "N/A";
            }

            ApproveList approveList = ApproveList.builder()
                    .id(approve.getId())
                    .name(employeeName)
                    .title(approve.getTitle())
                    .content(approve.getContent())
                    .createAt(approve.getCreateAt())
                    .updateAt(approve.getUpdateAt())
                    .confirmer1(confirmer1Name) // confirmer1의 이름 설정
                    .confirmer2(confirmer2Name) // confirmer2의 이름 설정
                    .status(approve.getStatus())
                    .build();

            approveLists.add(approveList);
        }

        return approveLists;
    }



    public ApproveRead read(Integer approveId) {
        Approve approve = approveRepository.findById(approveId)
                .orElseThrow(() -> new RuntimeException("결재 정보를 찾을 수 없습니다."));
        String confirmer1Name = "Unknown"; // 초기값 설정
        String confirmer2Name = "Unknown"; // 초기값 설정
        if (!approve.getApproveLines().isEmpty()) {
            ApproveLine firstApproveLine = approve.getApproveLines().get(0); // 첫 번째 ApproveLine 가져오기
            confirmer1Name = firstApproveLine.getConfirmer1() != null ? firstApproveLine.getConfirmer1().getName() : "N/A";
            confirmer2Name = firstApproveLine.getConfirmer2() != null ? firstApproveLine.getConfirmer2().getName() : "N/A";
        }
        return ApproveRead.builder()
                .id(approve.getId())
                .title(approve.getTitle())
                .confirmer1(confirmer1Name) // confirmer1의 이름 설정
                .confirmer2(confirmer2Name) // confirmer2의 이름 설정
                .content(approve.getContent())
                .build();
    }


    @Transactional
    public void update(ApproveUpdate approveUpdate) {
        Approve approve = approveRepository.findById(approveUpdate.getId())
                .orElseThrow(() -> new RuntimeException("결재 정보를 찾을 수 없습니다."));

        if (approve.getStatus() != 3) {
            throw new IllegalStateException("반려된 상태의 결재만 수정할 수 있습니다.");
        }

        approve.setTitle(approveUpdate.getTitle());
        approve.setContent(approveUpdate.getContent());
        approveRepository.save(approve);
    }

    @Transactional
    public void returnStatus(Integer id, Integer approveLineId) {
        ApproveLine approveLine = approveLineRepository.findById(approveLineId)
                .orElseThrow(() -> new RuntimeException("해당 ID의 결재 라인 정보를 찾을 수 없습니다."));

        Approve approve = approveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 결재 정보를 찾을 수 없습니다."));

        approve.setStatus(approve.getStatus());
        approveRepository.save(approve);
    }

    @Transactional
    public void delete(Integer approveId) {
        Approve approve = approveRepository.findById(approveId)
                .orElseThrow(() -> new RuntimeException("결재 정보를 찾을 수 없습니다."));
        approveFileRepository.deleteByApprove(approve); // ApproveFile 레코드 먼저 삭제
        approveRepository.delete(approve); // 이후 Approve 레코드 삭제
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

    public void saveFile(Integer id, String uploadPath) {
        approveFileRepository.save(ApproveFile.builder()
                .approve(Approve.builder().id(id).build())
                .filename(uploadPath)
                .build());
    }
}
