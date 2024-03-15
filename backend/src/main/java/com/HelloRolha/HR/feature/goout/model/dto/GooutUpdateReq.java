package com.HelloRolha.HR.feature.goout.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Builder
@Data
public class GooutUpdateReq {
    private Integer id;
    private LocalDate first;
    private LocalDate last;
    private Integer gooutTypeId;
    private Integer employeeId;
    private Integer agentId;
//    private List<MultipartFile> newFiles;  // 추가할 파일들
//    private List<Integer> deleteFileIds;   // 삭제할 파일의 ID들
}
