package com.HelloRolha.HR.feature.approve.model;


import com.HelloRolha.HR.common.entity.BaseEntity;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Approve extends BaseEntity {
    private String content;
    private String title;
    @Column(nullable = true)
    private Integer status =0;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "employee_id")
    private Employee employee; //작성자

    @OneToMany(mappedBy = "approve")
    private List<ApproveFile> approveFiles = new ArrayList<>();
    @OneToMany(mappedBy = "approve")
    private List<ApproveLine> approveLines = new ArrayList<>();
}