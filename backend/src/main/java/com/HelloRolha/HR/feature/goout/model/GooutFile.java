package com.HelloRolha.HR.feature.goout.model;

import com.HelloRolha.HR.feature.goout.model.Goout;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class GooutFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Goout_id")
    private Goout goout;

}