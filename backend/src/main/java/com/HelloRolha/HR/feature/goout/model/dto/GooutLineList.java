package com.HelloRolha.HR.feature.goout.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GooutLineList {
    private Integer id;
    private String confirmer1Name;
    private String confirmer2Name;
}
