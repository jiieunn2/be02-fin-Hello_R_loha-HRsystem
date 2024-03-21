package com.HelloRolha.HR.feature.goout.model;

import com.HelloRolha.HR.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GooutType extends BaseEntity {
    private String name;
    private String detail;
    private Integer maxHoliday;

    @OneToMany(mappedBy = "gooutType")
    private List<Goout> goouts = new ArrayList<>();

}
