package com.HelloRolha.HR.feature.approve.model.dto.Approve;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApproveList {
    private Integer id;
    private String name;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int status;

}
