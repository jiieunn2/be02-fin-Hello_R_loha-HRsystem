package com.HelloRolha.HR.feature.approve.model;

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
public class ApproveLine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime approveTime;

//    private LocalDateTime applyTime;

    private String comment;

    @ManyToOne @JoinColumn(name = "approve_id")
    private Approve approve;

    @ManyToOne @JoinColumn(name = "confirmer_id")
    private Employee confirmer;

    private String status = "PENDING";

    private Integer order;




}
