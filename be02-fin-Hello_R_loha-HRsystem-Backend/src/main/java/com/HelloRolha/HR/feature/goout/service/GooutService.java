package com.HelloRolha.HR.feature.goout.service;


import com.HelloRolha.HR.feature.goout.model.Goout;
import com.HelloRolha.HR.feature.goout.model.GooutFile;
import com.HelloRolha.HR.feature.goout.model.entity.GooutCreateReq;
import com.HelloRolha.HR.feature.goout.repo.GooutFileRepository;
import com.HelloRolha.HR.feature.goout.repo.GooutRepository;
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
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GooutService {
    private final GooutRepository gooutRepository;
    private final GooutFileRepository gooutFileRepository;
    private final AmazonS3 s3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public Goout create(GooutCreateReq gooutCreateReq) {

        return gooutRepository.save(Goout.builder()
                .period(gooutCreateReq.getPeriod())
                .agentId(gooutCreateReq.getAgentId())
                .typeId(gooutCreateReq.getTypeId())
                .employeeId(gooutCreateReq.getEmployeeId())
                .build());
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
