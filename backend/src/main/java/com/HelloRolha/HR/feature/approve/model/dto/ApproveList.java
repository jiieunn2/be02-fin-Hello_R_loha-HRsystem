package com.HelloRolha.HR.feature.approve.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApproveList {
    private Integer id;
    private String title;
    private LocalDateTime createTime;
    private int status;
}
