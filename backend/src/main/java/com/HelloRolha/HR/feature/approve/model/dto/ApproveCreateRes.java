package com.HelloRolha.HR.feature.approve.model.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
public class ApproveCreateRes {
    private Integer id;

    private String content;

    private String filename;

    private LocalDateTime correctionTime;

    private LocalDateTime createTime;

    private Integer status;

    private String title;

    private Integer employeeId;
}