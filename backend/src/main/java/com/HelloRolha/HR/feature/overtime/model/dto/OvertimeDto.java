package com.HelloRolha.HR.feature.overtime.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter
public class OvertimeDto {

    private Integer id;
    private String date;
    private String shift;
    private String startTime;
    private String endTime;
    private String reason;
    private String status;


}
