package com.HelloRolha.HR.feature.approve.model;

import com.HelloRolha.HR.common.entity.ApproveLineBaseEntity;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApproveLine extends ApproveLineBaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    @ManyToOne @JoinColumn(name = "approve_id")
    private Approve approve;

    private int order;

}
