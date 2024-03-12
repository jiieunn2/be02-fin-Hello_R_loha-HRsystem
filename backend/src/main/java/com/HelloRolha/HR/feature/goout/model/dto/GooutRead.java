package com.HelloRolha.HR.feature.goout.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class GooutRead {
    private Integer id;
    private LocalDateTime period;
    private String agentName;
    private String employeeName;
    private String gooutTypeName;
    private Integer status;
    private LocalDateTime first;
    private LocalDateTime last;
}
