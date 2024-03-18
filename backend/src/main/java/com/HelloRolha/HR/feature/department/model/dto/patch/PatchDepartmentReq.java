package com.HelloRolha.HR.feature.department.model.dto.patch;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchDepartmentReq {
    private Integer id;
    private Integer departmentNum;
    private String departmentName;
}
