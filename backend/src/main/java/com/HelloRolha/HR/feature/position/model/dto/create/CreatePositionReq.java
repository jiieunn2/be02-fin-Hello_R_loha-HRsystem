package com.HelloRolha.HR.feature.position.model.dto.create;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePositionReq {
    private Integer positionNum;
    private String positionName;
}
