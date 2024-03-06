package com.HelloRolha.HR.feature.employee.service;

import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.employee.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public void signUp(String username, String password) {
        employeeRepository.save(Employee.builder()
                        .username(username)
                        .password(password)
                .build());

    }
}
