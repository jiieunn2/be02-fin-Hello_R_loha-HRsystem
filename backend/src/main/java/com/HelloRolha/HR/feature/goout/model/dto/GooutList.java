package com.HelloRolha.HR.feature.goout.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class GooutList {
    private Integer id;
    private String name;
    private String gooutTypeName;
    private Integer status;
    private LocalDate first;
    private LocalDate last;
}
