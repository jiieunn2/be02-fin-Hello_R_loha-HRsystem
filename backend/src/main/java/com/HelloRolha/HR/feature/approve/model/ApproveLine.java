package com.HelloRolha.HR.feature.approve.model;

import com.HelloRolha.HR.common.entity.ApproveLineBaseEntity;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ApproveLine extends ApproveLineBaseEntity {


    @ManyToOne
    @JoinColumn(name = "approve_id")
    private Approve approve;

    //private int order; // 이거 어디다가 쓰는 거지?

}
