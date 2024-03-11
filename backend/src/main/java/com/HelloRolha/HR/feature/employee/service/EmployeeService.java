package com.HelloRolha.HR.feature.employee.service;

import com.HelloRolha.HR.config.utils.JwtUtils;
import com.HelloRolha.HR.error.UserAccountException;
import com.HelloRolha.HR.error.UserNotFoundException;
import com.HelloRolha.HR.feature.department.model.entity.Department;
import com.HelloRolha.HR.feature.employee.model.dto.Login.LoginReq;
import com.HelloRolha.HR.feature.employee.model.dto.Login.LoginRes;
import com.HelloRolha.HR.feature.employee.model.dto.SignUp.SignUpReq;
import com.HelloRolha.HR.feature.employee.model.dto.SignUp.SignUpRes;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.employee.repo.EmployeeRepository;
import com.HelloRolha.HR.feature.position.model.entity.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;
    public SignUpRes signUp(SignUpReq signUpReq) {
        Employee employee= Employee.builder()
                .username(signUpReq.getUsername())
                .password(passwordEncoder.encode(signUpReq.getPassword()))
                .name(signUpReq.getName())
                .phoneNum(signUpReq.getPhoneNum())
                .birth(signUpReq.getBirth())
                .age(signUpReq.getAge())
                .address(signUpReq.getAddress())
                .employmentDate(LocalDate.now())
                .department(Department.builder().id(signUpReq.getDepartmentId()).build())
                .position(Position.builder().id(signUpReq.getPositionId()).build())
                .build();
        employeeRepository.save(employee);

        return SignUpRes.builder()
                .result(true)
                .build();
    }

    public LoginRes login(LoginReq loginReq) {
        Optional<Employee> result = employeeRepository.findByUsername(loginReq.getUsername());

        if(result.isEmpty()) {
            throw UserNotFoundException.forEmail(loginReq.getUsername());
        }

        Employee employee = result.get();
        if (passwordEncoder.matches(loginReq.getPassword(), employee.getPassword()) && employee.getStatus().equals(true)) {
            return LoginRes.builder()
                    .token(JwtUtils.generateAccessToken(employee, secretKey, expiredTimeMs))
                    .build();


        } else {
            throw UserAccountException.forInvalidPassword(loginReq.getPassword());
        }
    }
}
