package com.HelloRolha.HR.feature.approve.model.dto.Approve;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
public class ApproveCreateReq {
    //private Integer id;

    private String content;

    private Integer status;

    private String title;

    //private Integer employeeId; // 작성자?
}