package com.HelloRolha.HR.feature.overtime.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    @Builder
    public Overtime(Integer id, String shift, String startTime, String endTime,String reason, String date) {
        this.id = id;
        this.shift = shift;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.date = date;
    }
}
