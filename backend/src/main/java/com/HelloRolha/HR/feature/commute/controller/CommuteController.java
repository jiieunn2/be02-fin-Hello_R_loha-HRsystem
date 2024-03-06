package com.HelloRolha.HR.feature.commute.controller;


import com.HelloRolha.HR.feature.commute.service.CommuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class CommuteController {
    @Autowired
    private CommuteService commuteService;

    public CommuteController(CommuteService commuteService) {
        this.commuteService=commuteService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/commute")
    public ResponseEntity commute(Integer id) {
        return ResponseEntity.ok().body(commuteService.commute(id));
    }

//    @PostMapping("/checkout")
//    public EmployeeDto checkOut() {
//        return employeeService.checkOut();
//    }
}