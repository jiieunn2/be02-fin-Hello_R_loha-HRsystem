package com.HelloRolha.HR.feature.department.model.entity;

import com.HelloRolha.HR.common.entity.BaseEntity;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity {

    private Integer departmentNum;
    private String departmentName;
    @OneToMany(mappedBy="department")
    List<Employee> employees;
}
