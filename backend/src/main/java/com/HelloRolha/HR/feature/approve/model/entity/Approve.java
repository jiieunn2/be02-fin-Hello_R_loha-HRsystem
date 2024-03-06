package com.HelloRolha.HR.feature.approve.model.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Approve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private String filename;

    private LocalDateTime correction_time;

    private LocalDateTime create_time;

    private Integer status;

    private String title;

    private Integer employee_id;

    // Getters and Setters
}