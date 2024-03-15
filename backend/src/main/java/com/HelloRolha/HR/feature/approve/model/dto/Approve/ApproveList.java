package com.HelloRolha.HR.feature.approve.model.dto.Approve;

import lombok.*;

import java.time.LocalDateTime;

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
    private String confirmer1; // confirmer1 필드 추가
    private String confirmer2; // confirmer2 필드 추가
    private int status;

}
