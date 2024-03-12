package com.HelloRolha.HR.feature.approve.model.dto.ApproveLine;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApproveLineConfirm {
    private Integer confirmer1Id;
    private Integer confirmer2Id;
    private Integer approveId;
    private String comment;
}
