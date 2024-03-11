package com.HelloRolha.HR.feature.manager.service;

import com.HelloRolha.HR.error.UserNotFoundException;
import com.HelloRolha.HR.feature.commute.model.Commute;
import com.HelloRolha.HR.feature.commute.model.dto.CommuteDto;
import com.HelloRolha.HR.feature.commute.repository.CommuteRepository;
import com.HelloRolha.HR.feature.commute.service.CommuteService;
import com.HelloRolha.HR.feature.employee.model.dto.EmployeeDto;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.employee.repo.EmployeeRepository;
import com.HelloRolha.HR.feature.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final EmployeeService employeeService;
    private final CommuteService commuteService;

    public Object authorize(Integer employeeId) {
        return employeeService.authorize(employeeId);
    }

    public Object findAuthorize() {
        return employeeService.findAuthorize();
    }

    public Object listEmployee() {
        return employeeService.listEmployee();
    }

    public Object readEmployee(Integer employeeId) {
        return employeeService.readEmployee(employeeId);
    }

    //Todo commute list 확인하기.
//    public Object listCommute() {
//        return commuteService.listCommute();
//    }
// To do QueryDsl로 쿼리 작성해서 해결하자.
//    public Object checkToday() { // 오늘 직원들이 출근했는지 확인하는 거
//        return commuteService.checkToday();
//    }
}
