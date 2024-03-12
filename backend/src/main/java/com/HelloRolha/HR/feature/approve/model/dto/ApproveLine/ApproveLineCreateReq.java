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
public class ApproveLineCreateReq {
    private Integer approveId;
    private Integer confirmerId;
    private String comment;

}
