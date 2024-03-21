package com.HelloRolha.HR.feature.employee.model.entity;
import com.HelloRolha.HR.common.entity.BaseEntity;
import com.HelloRolha.HR.feature.approve.model.Approve;
import com.HelloRolha.HR.feature.commute.model.Commute;
import com.HelloRolha.HR.feature.department.model.entity.Department;
import com.HelloRolha.HR.feature.goout.model.Goout;

import com.HelloRolha.HR.feature.overtime.model.Overtime;
import com.HelloRolha.HR.feature.position.model.entity.Position;


import com.HelloRolha.HR.feature.salary.model.entity.Salary;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert ////ToDo
public class Employee extends BaseEntity implements UserDetails {
    private String username;
    private String password;
//    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'ROLE_NEW'")
    @ColumnDefault("'ROLE_NEW'")
    private String authority;
    private LocalDate employmentDate;
    @ColumnDefault("false")
    private Boolean status;

    //details
    private String name;
    private String phoneNum;
    private String birth;
    private String address;
    private Integer age;
    @ColumnDefault("5000000")
    private Long salary;

    //외래키
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Department_id")
    private Department department;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Position_id")
    private Position position;


    @OneToMany(mappedBy = "employee")
    private List<Salary> salaryList = new ArrayList<>();

    // 사용 기능들
    @OneToMany(mappedBy = "employee")
    private List<Goout> goouts = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Approve> approves = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Commute> commutes = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Overtime> overtimes = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> this.authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
    //

}
