package com.HelloRolha.HR.feature.approve.model;

import com.HelloRolha.HR.common.entity.FileBaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ApproveFile extends FileBaseEntity {
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "approve_id")
    private Approve approve;

}
