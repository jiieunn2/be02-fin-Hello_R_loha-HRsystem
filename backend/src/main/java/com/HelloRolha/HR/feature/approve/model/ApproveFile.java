package com.HelloRolha.HR.feature.approve.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ApproveFile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "approve_id")
    private Approve approve;

//    @UpdateTimestamp
//    @Column(nullable = true)
//    private LocalDateTime correctionTime;
//
//    @UpdateTimestamp
//    private LocalDateTime createTime;





}
