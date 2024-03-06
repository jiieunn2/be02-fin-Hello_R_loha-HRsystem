package com.HelloRolha.HR.feature.commute.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
public class EmployeeDto {

    private Integer id;
    private LocalDateTime start_time;
    private LocalDateTime end_time;


}








