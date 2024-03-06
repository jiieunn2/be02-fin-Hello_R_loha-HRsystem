package com.HelloRolha.HR.feature.employee.model.entity;



import com.HelloRolha.HR.common.entity.BaseEntity;
import com.HelloRolha.HR.feature.department.model.entity.Department;
import com.HelloRolha.HR.feature.position.model.entity.Position;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity {

    private String username;
    private String password;
    private String authory;
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
}
