package com.HelloRolha.HR.feature.goout.model;


import com.HelloRolha.HR.common.entity.BaseEntity;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Goout extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    private LocalDateTime period;
    private Integer status;
//    private Integer correctionTime;       >   BaseEntity
//    private Integer createTime;           >   BaseEntity

//  임시
//    private Integer typeId;
    private String type;
    private LocalDateTime first;
    private LocalDateTime last;
//  임시

    @OneToMany(mappedBy = "goout")
    private List<GooutFile> gooutFiles = new ArrayList<>();

    @OneToMany(mappedBy = "goout")
    private List<GooutType> gooutTypes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agent_id")
    private Employee agent;
}
