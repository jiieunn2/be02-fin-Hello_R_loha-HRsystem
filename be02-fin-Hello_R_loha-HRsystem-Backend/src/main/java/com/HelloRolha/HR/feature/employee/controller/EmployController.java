package com.HelloRolha.HR.feature.employee.controller;

import com.HelloRolha.HR.feature.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployController {

    private final EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity hello(String username,String password) {
        employeeService.signUp(username,password);
        return ResponseEntity.ok().body("v3");
    }
}
