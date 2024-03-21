package com.HelloRolha.HR.feature.employee.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalaryDto {
    private Integer id;
    private String name;
    private LocalDate employmentDate;
    private String department;
    private String position;
    private Integer salary;
}
