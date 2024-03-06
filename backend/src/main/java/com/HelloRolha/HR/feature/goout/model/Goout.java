package com.HelloRolha.HR.feature.goout.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    private Integer correctionTime;
//    private Integer createTime;
    private LocalTime period;
    private Integer agentId;
    private Integer typeId;
    private Integer employeeId;

    @OneToMany(mappedBy = "goout")
    private List<GooutFile> gooutFiles = new ArrayList<>();
}
