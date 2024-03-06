package com.HelloRolha.HR.feature.position.controller;

import com.HelloRolha.HR.feature.employee.service.EmployeeService;
import com.HelloRolha.HR.feature.position.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/position")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(Integer positionNum, String positionName) {
        positionService.create(positionNum,positionName);
        return ResponseEntity.ok().body("v3");
    }
}
