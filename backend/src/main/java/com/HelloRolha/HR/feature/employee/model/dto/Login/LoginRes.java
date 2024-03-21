package com.HelloRolha.HR.feature.employee.model.dto.Login;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {
    private String name;
    private String token;
}
