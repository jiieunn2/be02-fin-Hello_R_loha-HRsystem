package com.HelloRolha.HR.feature.goout.model;


import com.HelloRolha.HR.common.entity.ApproveLineBaseEntity;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GooutLine extends ApproveLineBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "confirmer1_id")
    private Employee confirmer1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "confirmer2_id")
    private Employee confirmer2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goout_id")
    private Goout goout;
}
