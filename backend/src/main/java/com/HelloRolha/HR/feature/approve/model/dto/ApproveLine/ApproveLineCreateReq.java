package com.HelloRolha.HR.feature.approve.model.dto.ApproveLine;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class ApproveLineCreateReq {
    private Integer EmployeeId;
    private Integer confirmer1Id;
    private Integer confirmer2Id;
    private Integer approveId;

}
