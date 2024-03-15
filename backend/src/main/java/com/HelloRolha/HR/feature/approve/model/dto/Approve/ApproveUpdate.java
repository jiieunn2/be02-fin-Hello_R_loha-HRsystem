package com.HelloRolha.HR.feature.approve.model.dto.Approve;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApproveUpdate {
    private Integer id;

    private String title;

    private String content;

    private List<MultipartFile> newFiles;

    private List<Integer> deleteFileIds;

}
