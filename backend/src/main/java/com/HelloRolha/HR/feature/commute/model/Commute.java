package com.HelloRolha.HR.feature.commute.model;



import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.position.model.entity.Position;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Commute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String sumTime;

    public Commute(Integer id, LocalDateTime startTime, LocalDateTime endTime, String sumTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sumTime = sumTime;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Employee_id")
    private Employee employee;
}

