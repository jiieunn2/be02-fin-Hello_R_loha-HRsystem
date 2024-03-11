package com.HelloRolha.HR.feature.employee.model.entity;
import com.HelloRolha.HR.common.entity.BaseEntity;
import com.HelloRolha.HR.feature.approve.model.Approve;
import com.HelloRolha.HR.feature.commute.model.Commute;
import com.HelloRolha.HR.feature.department.model.entity.Department;
import com.HelloRolha.HR.feature.goout.model.Goout;
import com.HelloRolha.HR.feature.goout.model.GooutFile;
import com.HelloRolha.HR.feature.position.model.entity.Position;


import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity {
    // private Integer remainingVacationDays;      //Goout에서 사용하기 위해 임시로 만들었는데, 추후 Goout쪽에서 완성 시 삭제예정

    private String username;
    private String password;
    @Column(nullable = false, columnDefinition = "DEFAULT 'NEW'")
    private String authority;

    //private Integer employeeNum; // 사원 번호
    private LocalDate employmentDate;

    @Column(nullable = false, columnDefinition = "DEFAULT False")
    private Boolean status;

    //details
    private String name;
    private String phoneNum;
    private LocalDate birth;
    private String address;
    private Integer age;

    //외래키 TODO 직원은 하나의 포지션을 가지지만 한 포지션을 다수의 직원이 가질 수 있음. 관계 추가해야함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Department_id")
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Position_id")
    private Position position;

    // 사용 기능들
    @OneToMany(mappedBy = "employee")
    private List<Goout> goouts = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Approve> approves = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Commute> commutes = new ArrayList<>();
    //

}
