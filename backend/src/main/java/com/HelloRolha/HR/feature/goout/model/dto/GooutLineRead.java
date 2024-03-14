package com.HelloRolha.HR.feature.goout.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class GooutLineRead {
    private Integer confirmer1Id;
    private String confirmer1Name;
    private Integer confirmer2Id;
    private String confirmer2Name;
    private Integer gooutId;
    private String comment;
    private LocalDateTime approveTime;
    private LocalDateTime applyTime;
    private Integer status;
}
