package com.HelloRolha.HR.feature.goout.model.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GooutCreateRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private GooutCreateResult result;
    private Boolean success;
}
