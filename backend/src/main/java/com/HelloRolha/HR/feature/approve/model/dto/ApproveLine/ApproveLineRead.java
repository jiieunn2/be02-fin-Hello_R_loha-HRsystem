package com.HelloRolha.HR.feature.approve.model.dto.ApproveLine;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ApproveLineRead {
    private Integer confirmer1Id;
    private Integer confirmer2Id;
    private Integer approveId;
    private String comment;
    private LocalDateTime approveTime;
    private LocalDateTime applyTime;
    private Integer status;
}
