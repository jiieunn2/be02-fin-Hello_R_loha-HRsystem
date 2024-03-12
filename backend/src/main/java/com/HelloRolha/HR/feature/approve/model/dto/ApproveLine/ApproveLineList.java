package com.HelloRolha.HR.feature.approve.model.dto.ApproveLine;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApproveLineList {
    private Integer id;
    private String confirmer1Name;
    private String confirmer2Name;
}
