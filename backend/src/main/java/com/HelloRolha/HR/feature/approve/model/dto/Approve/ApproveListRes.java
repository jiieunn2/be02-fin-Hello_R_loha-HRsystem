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
public class ApproveListRes {
    private Integer id;
    private String title;
    private LocalDateTime createTime;
    private int status;
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private List<ApproveList> result;
    private Boolean success;

}
