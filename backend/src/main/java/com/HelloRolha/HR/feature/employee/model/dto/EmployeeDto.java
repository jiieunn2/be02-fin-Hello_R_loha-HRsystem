package com.HelloRolha.HR.feature.employee.model.dto;

import com.HelloRolha.HR.feature.department.model.entity.Department;
import com.HelloRolha.HR.feature.position.model.entity.Position;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Integer id;
    private String name;
    private LocalDate employmentDate;
    private String department;
    private String position;
    private Long salary;
}
