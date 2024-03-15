package com.HelloRolha.HR.feature.salary.model.dto;

import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.salary.model.entity.Salary;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SalaryDto {
    //직원 정보

    private Integer employeeId;
    private LocalDate employmentDate;
    private String department;
    private String position;
    private String employeeName; // 직원 이름
    private Long employeeSalary; //직원 급여

    private LocalDate batchDate;
    private Long commuteCount; // 출근 시간
    private Integer paidVacationCount;
    private Long overTime; // 초과근무 시간
    private Long totalSalary;

    public Salary toEntity(){
        return Salary.builder()
                .employee(Employee.builder().id(this.employeeId).build())
                .salaryDate(this.batchDate)
                .commuteCount(this.commuteCount)
                .overTime(this.overTime)
                .paidVacationCount(this.paidVacationCount)
                .totalSalary(this.totalSalary)
                .build();
    }

}
