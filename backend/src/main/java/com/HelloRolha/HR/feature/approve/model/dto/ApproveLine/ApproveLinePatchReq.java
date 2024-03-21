package com.HelloRolha.HR.feature.approve.model.dto.ApproveLine;

import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
public class ApproveLinePatchReq {
    private Employee confirmerId;
    private String comment;
    private LocalDateTime approveTime;
    private LocalDateTime applyTime;
    private Integer status;
}
