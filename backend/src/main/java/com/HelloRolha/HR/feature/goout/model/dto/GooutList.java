package com.HelloRolha.HR.feature.goout.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class GooutList {
    private Integer id;
    private String name;
    private LocalDateTime period;
    private String gooutTypeName;
    private LocalDateTime first;
    private LocalDateTime last;
}
