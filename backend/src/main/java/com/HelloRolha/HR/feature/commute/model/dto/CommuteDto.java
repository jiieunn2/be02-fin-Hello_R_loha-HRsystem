package com.HelloRolha.HR.feature.commute.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class CommuteDto {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String sumTime;
    private String employeeName;
}


