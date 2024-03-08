package com.HelloRolha.HR.feature.goout.model.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GooutReturnRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private Boolean success;
}