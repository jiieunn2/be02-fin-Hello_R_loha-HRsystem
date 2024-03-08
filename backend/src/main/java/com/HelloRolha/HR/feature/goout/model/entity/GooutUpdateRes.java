package com.HelloRolha.HR.feature.goout.model.entity;

import com.HelloRolha.HR.feature.goout.model.Goout;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GooutUpdateRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private GooutUpdateResult result;
    private Boolean success;
}