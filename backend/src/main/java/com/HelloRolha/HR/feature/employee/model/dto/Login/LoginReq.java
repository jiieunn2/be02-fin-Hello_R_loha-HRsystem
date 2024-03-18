package com.HelloRolha.HR.feature.employee.model.dto.Login;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {
    private String username;
    private String password;
}
