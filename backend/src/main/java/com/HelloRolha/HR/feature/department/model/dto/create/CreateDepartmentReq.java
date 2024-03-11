package com.HelloRolha.HR.feature.department.model.dto.create;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDepartmentReq {
    private Integer departmentNum;
    private String departmentName;
}
