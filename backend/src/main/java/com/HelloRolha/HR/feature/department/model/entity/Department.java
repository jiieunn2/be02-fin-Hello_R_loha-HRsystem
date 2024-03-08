package com.HelloRolha.HR.feature.department.model.entity;

import com.HelloRolha.HR.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity {
    private Integer departmentNum;
    private String departmentName;
}
