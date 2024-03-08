package com.HelloRolha.HR.feature.approve.model.dto;

import com.HelloRolha.HR.feature.approve.model.ApproveFile;
import com.HelloRolha.HR.feature.approve.model.ApproveLine;
import lombok.*;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApproveUpdateRes {

    private String title;

    private String content;

    private String filename;

    @OneToMany(mappedBy = "approve")
    private List<ApproveFile> approveFiles = new ArrayList<>();

    @OneToMany(mappedBy = "approve")
    private List<ApproveLine> approveLines = new ArrayList<>();
}
