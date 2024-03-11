package com.HelloRolha.HR.feature.employee.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.employee.model.dto.SignUp.SignUpReq;
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
public class EmployeeController {

    private final EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signUp(SignUpReq signUpReq) {

        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("회원 가입 성공")
                .result(employeeService.signUp(signUpReq))
                .build();


        return ResponseEntity.ok().body(res);
    }
}
