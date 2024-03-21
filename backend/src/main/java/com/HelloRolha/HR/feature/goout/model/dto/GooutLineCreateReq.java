package com.HelloRolha.HR.feature.goout.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class GooutLineCreateReq {
    private Integer confirmer1Id;
    private Integer confirmer2Id;
    private Integer gooutId;
    private Integer EmployeeId;
}
