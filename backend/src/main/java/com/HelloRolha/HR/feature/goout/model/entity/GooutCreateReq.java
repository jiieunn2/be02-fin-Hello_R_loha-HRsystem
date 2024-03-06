package com.HelloRolha.HR.feature.goout.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Builder
@Data
public class GooutCreateReq {
    private LocalTime period;
    private Integer agentId;
    private Integer typeId;
    private Integer employeeId;
}
