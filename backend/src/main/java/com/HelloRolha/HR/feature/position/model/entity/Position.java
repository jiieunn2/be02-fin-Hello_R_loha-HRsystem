package com.HelloRolha.HR.feature.position.model.entity;

import com.HelloRolha.HR.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Position extends BaseEntity {
    private Integer positionNum;
    private String positionName;
}
