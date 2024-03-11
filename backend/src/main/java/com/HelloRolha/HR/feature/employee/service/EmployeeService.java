package com.HelloRolha.HR.feature.employee.service;

import com.HelloRolha.HR.feature.employee.model.dto.SignUp.SignUpReq;
import com.HelloRolha.HR.feature.employee.model.dto.SignUp.SignUpRes;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.employee.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public SignUpRes signUp(SignUpReq signUpReq) {
        employeeRepository.save(Employee.builder()
                        .username(signUpReq.getUsername())
                        .password(signUpReq.getPassword())
                        .birth(signUpReq.getBirth())
                        .age(signUpReq.getAge())
                        .address(signUpReq.getAddress())
                        .employmentDate(LocalDate.now())
                .build());

        return SignUpRes.builder()
                .result(true)
                .build();
    }
}
