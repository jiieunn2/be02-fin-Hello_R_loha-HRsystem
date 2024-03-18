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
    private String filename;

    //임시
    //private Integer remainingVacationDays;
    //private String position;
//    private Integer typeId;
    private String type;
    private LocalDateTime first;
    private LocalDateTime last;
    //임시

}
