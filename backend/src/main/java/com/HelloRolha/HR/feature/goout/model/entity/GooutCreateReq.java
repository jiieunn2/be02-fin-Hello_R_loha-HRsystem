package com.HelloRolha.HR.feature.goout.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class GooutCreateReq {
    private LocalDateTime period;
    private Integer agentId;
    private Integer employeeId;
    //
//    private Integer typeId;
    private String type;
    private LocalDateTime first;
    private LocalDateTime last;
    //
}
