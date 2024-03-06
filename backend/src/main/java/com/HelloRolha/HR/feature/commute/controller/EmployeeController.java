package com.HelloRolha.HR.feature.commute.controller;


import com.HelloRolha.HR.feature.commute.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService=employeeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/attend")
    public ResponseEntity attend(Integer id) {
        return ResponseEntity.ok().body(employeeService.attend(id));
    }

//    @PostMapping("/checkout")
//    public EmployeeDto checkOut() {
//        return employeeService.checkOut();
//    }
}