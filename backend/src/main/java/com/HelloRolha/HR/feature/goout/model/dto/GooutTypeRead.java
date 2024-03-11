package com.HelloRolha.HR.feature.goout.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GooutTypeRead {
    private String name;
    private String detail;
    private Integer maxHoliday;
    private boolean includesWeekends; // 주말 및 공휴일 포함 여부
    private boolean expiresAtYearEnd; // 연말에 소멸 여부
}
