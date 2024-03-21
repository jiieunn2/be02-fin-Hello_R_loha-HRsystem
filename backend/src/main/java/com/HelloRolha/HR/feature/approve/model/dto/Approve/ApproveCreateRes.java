package com.HelloRolha.HR.feature.approve.model.dto.Approve;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
public class ApproveCreateRes {
    private Integer id;

    private String content;

    private String filename;

    @CreatedDate
    private LocalDateTime createAt;

    private Integer status;

    private String title;

    private Integer employeeId;
}