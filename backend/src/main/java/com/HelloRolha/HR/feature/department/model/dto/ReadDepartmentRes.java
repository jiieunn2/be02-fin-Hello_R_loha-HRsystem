package com.HelloRolha.HR.feature.department.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadDepartmentRes {
    private Integer id;
    private Integer departmentNum;
    private String departmentName;
}
