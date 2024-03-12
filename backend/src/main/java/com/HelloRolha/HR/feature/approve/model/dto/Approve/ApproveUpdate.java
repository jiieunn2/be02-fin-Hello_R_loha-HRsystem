package com.HelloRolha.HR.feature.approve.model.dto.Approve;

import com.HelloRolha.HR.feature.approve.model.ApproveFile;
import com.HelloRolha.HR.feature.approve.model.ApproveLine;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.OneToMany;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
