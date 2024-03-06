package com.HelloRolha.HR.common.entity;

import com.HelloRolha.HR.feature.employee.model.entity.Employee;

import java.time.LocalDateTime;

public class ApproveLineBaseEntity extends BaseEntity {
    private Employee confirmer1Id;
    private Employee confirmer2Id;
    private String comment;
    private LocalDateTime approveTime;
    private LocalDateTime applyTime;
    private String status;
}
