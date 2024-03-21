package com.HelloRolha.HR.feature.goout.model;


import com.HelloRolha.HR.common.entity.BaseEntity;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Goout extends BaseEntity {
    private Integer status;
    private LocalDate first;
    private LocalDate last;

    @OneToMany(mappedBy = "goout")
    private List<GooutFile> gooutFiles = new ArrayList<>();

    @OneToMany(mappedBy = "goout")
    private List<GooutLine> gooutLines = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agent_id")
    private Employee agent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gooutType_id")
    private GooutType gooutType;
}
