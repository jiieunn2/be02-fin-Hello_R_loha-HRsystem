package com.HelloRolha.HR.feature.goout.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class GooutCreateReq {
    private Integer agentId;
    private Integer employeeId;
    private Integer gooutTypeId;
    private LocalDate first;
    private LocalDate last;
    //
}
