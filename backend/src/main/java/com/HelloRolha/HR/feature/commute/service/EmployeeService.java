package com.HelloRolha.HR.feature.commute.service;


import com.HelloRolha.HR.feature.commute.model.Employee;
import com.HelloRolha.HR.feature.commute.model.dto.EmployeeDto;
import com.HelloRolha.HR.feature.commute.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public EmployeeDto attend(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(""));

        return EmployeeDto.builder()
                .id(employee.getId())
                .start_time(employee.getStart_time())
                .end_time(employee.getEnd_time())
                .build();
    }

}