package com.HelloRolha.HR.feature.overtime.model;

import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Overtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;
    private String shift;
    private String startTime;
    private String endTime;
    private String reason;
    private String status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Builder
    public Overtime(Integer id, String shift, String startTime, String endTime,String reason, String date, String status,Employee employee) {
        this.id = id;
        this.shift = shift;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.date = date;
        this.status = status;
        this.employee = employee;
    }
}
