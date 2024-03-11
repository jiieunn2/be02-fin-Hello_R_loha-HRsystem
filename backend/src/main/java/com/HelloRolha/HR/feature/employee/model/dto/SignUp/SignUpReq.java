package com.HelloRolha.HR.feature.employee.model.dto.SignUp;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpReq {
    private String username;
    private String password;
    private String name;
    private String phoneNum;
    private LocalDate birth;
    private String address;
    private Integer age;
    private Integer positionId;
    private Integer departmentId;
}
