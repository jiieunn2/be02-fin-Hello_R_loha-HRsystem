package com.HelloRolha.HR.feature.goout.model.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GooutListRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private List<GooutList> result;
    private Boolean success;
}
