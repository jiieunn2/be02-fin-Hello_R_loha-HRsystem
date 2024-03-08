package com.HelloRolha.HR.feature.employee.model.entity;
import com.HelloRolha.HR.common.entity.BaseEntity;
import com.HelloRolha.HR.feature.department.model.entity.Department;
import com.HelloRolha.HR.feature.goout.model.Goout;
import com.HelloRolha.HR.feature.goout.model.GooutFile;
import com.HelloRolha.HR.feature.position.model.entity.Position;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity {

    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer remainingVacationDays;      //Goout에서 사용하기 위해 임시로 만들었는데, 추후 Goout쪽에서 완성 시 삭제예정
    private String position;
    //

    private String username;
    private String password;
    private String authority;
    private Integer employeeNum;
    private LocalDate employmentDate;
    private Boolean status;

    //details
    private String name;
    private String phoneNum;
    private LocalDate birth;
    private String address;
    private Integer age;

    //외래키 TODO 관계 추가해야함
//    private Department department;
//    private Position position;

    //
    @OneToMany(mappedBy = "employee")
    private List<Goout> goouts = new ArrayList<>();
    //

}
