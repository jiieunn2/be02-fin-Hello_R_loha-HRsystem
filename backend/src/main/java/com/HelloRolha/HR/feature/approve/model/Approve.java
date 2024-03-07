package com.HelloRolha.HR.feature.approve.model;


import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Approve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private String filename;

//    @LastModifiedDate
//    private LocalDateTime correctionTime;
//
//    @CreatedDate
//    private LocalDateTime createTime;

    private Integer status = 0;

    private String title;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "approve")
    private List<ApproveFile> approveFiles = new ArrayList<>();
    @OneToMany(mappedBy = "approve")
    private List<ApproveLine> approveLines = new ArrayList<>();


}