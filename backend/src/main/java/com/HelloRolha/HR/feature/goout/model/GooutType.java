package com.HelloRolha.HR.feature.goout.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GooutType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //                  >   BaseEntity
    //    private Integer correctionTime;   >   BaseEntity
    //    private Integer createTime;       >   BaseEntity

    private String name;
    private String detail;
    private Integer maxHoliday;
    private boolean includesWeekends; // 주말 및 공휴일 포함 여부

    //
    private boolean expiresAtYearEnd; // 연말에 소멸 여부
    //

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Goout_id")
    private Goout goout;
}
