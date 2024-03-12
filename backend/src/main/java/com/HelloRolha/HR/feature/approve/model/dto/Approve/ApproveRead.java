package com.HelloRolha.HR.feature.approve.model.dto.Approve;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@Getter
@Setter
public class ApproveRead {
    private Integer id;
    private String title;
    private LocalDateTime createTime;
    private String content;


}
