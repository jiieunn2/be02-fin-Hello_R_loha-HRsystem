package com.HelloRolha.HR.feature.goout.model.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class GooutLineUpdateReq {
    private Integer confirmer1Id;
    private Integer confirmer2Id;
    private Integer gooutId;
    private Integer employeeId;
}
