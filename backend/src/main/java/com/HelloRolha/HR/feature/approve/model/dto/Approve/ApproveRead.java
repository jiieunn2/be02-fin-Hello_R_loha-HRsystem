package com.HelloRolha.HR.feature.approve.model.dto.Approve;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
public class ApproveRead {
    private Integer id;
    private String title;
    private String content;
    private Integer status;
    private String confirmer1; // confirmer1 필드 추가
    private String confirmer2; // confirmer2 필드 추가

}
