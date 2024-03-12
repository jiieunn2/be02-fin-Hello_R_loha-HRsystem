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
    private boolean includesWeekends; // 주말 및 공휴일 포함 여부

    //
    private boolean expiresAtYearEnd; // 연말에 소멸 여부
    //

    @OneToMany(mappedBy = "gooutType")
    private List<Goout> goouts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Goout_id")
    private Goout goout;
}
