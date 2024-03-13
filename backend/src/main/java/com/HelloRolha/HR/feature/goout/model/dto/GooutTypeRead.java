package com.HelloRolha.HR.feature.goout.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GooutTypeRead {
    private String name;
    private String detail;
    private Integer maxHoliday;
}
