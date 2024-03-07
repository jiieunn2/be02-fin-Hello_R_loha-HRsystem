package com.HelloRolha.HR.feature.approve.service;

import com.HelloRolha.HR.feature.approve.model.Approve;
import com.HelloRolha.HR.feature.approve.model.ApproveLine;
import com.HelloRolha.HR.feature.approve.model.dto.ApproveCreateReq;
import com.HelloRolha.HR.feature.approve.repo.ApproveFileRepository;
import com.HelloRolha.HR.feature.approve.repo.ApproveLineRepository;
import com.HelloRolha.HR.feature.approve.repo.ApproveRepository;
import com.HelloRolha.HR.feature.employee.repo.EmployeeRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public ApproveCreateReq create(ApproveCreateReq approveCreateReq) {
        Approve approve = Approve.builder().content(approveCreateReq.getContent()).filename(approveCreateReq.getFilename()).title(approveCreateReq.getTitle()).build();

        approve = approveRepository.save(approve);

        return ApproveCreateReq.builder().id(approve.getId()).content(approve.getContent()).filename(approve.getFilename()).status(approve.getStatus()).title(approve.getTitle()).build();
    }

    public void processApproval(Integer approveId, Integer confirmerId, String decision) {
        List<ApproveLine> lines = approveLineRepository.findByApproveIdOrderByOrderAsc(approveId);
        for (ApproveLine line : lines) {
            if (line.getConfirmer().getId().equals(confirmerId) && line.getStatus().equals("PENDING")) {
                // 결재자의 결재 처리 (승인 또는 거절)
                line.setStatus(decision); // "APPROVED" 또는 "REJECTED"
                line.setApproveTime(LocalDateTime.now());
                approveLineRepository.save(line);

                // 다음 결재자 결재 가능 상태로 변경
                int nextOrder = line.getOrder() + 1;
                if (lines.size() >= nextOrder) {
                    ApproveLine nextLine = lines.get(nextOrder - 1);
                    if ("PENDING".equals(nextLine.getStatus())) {
                        // 다음 결재자에게 결재 권한 부여 (예: 알림 전송 등)
                        granttonextconfirmer(nextLine);
                    }
                }
                break;
            }
        }
    }

    public void granttonextconfirmer(ApproveLine currentApproveLine) {
        List<ApproveLine> approveLines = currentApproveLine.getApprove().getApproveLines();

        int currentIndex = approveLines.indexOf(currentApproveLine);
        int nextIndex = currentIndex + 1;

        if (nextIndex < approveLines.size()) {
            ApproveLine nextApproveLine = approveLines.get(nextIndex);
            nextApproveLine.setStatus("WAITING_FOR_APPROVAL");
            approveLineRepository.save(nextApproveLine);

        }
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
}
