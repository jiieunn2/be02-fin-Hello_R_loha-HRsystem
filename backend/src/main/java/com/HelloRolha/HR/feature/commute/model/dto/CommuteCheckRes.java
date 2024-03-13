package com.HelloRolha.HR.feature.commute.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommuteCheckRes {
    private Integer id;
    private Boolean isCommute;
}
