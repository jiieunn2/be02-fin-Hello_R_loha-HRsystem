package com.HelloRolha.HR.feature.commute.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Commute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime sumTime;

    @Builder
    public Commute(Integer id, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime sumTime){
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sumTime = sumTime;
    }

}
