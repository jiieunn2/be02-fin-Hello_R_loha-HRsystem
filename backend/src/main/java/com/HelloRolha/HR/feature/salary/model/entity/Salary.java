package com.HelloRolha.HR.feature.salary.model.entity;

import com.HelloRolha.HR.common.entity.BaseEntity;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert ////ToDo
public class Salary extends BaseEntity {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate salaryDate; // 월급날 배치처리 한 날
    private Long commuteCount; // 전 월 출근 시간
    private Integer paidVacationCount; //전 월 휴가 일수
    private Long overTime; // 전 월 초과근무 시간
    private Long totalSalary;
}
