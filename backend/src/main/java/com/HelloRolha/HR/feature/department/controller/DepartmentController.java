package com.HelloRolha.HR.feature.department.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.department.model.dto.create.CreateDepartmentReq;
import com.HelloRolha.HR.feature.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
@CrossOrigin("*")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    @PostMapping("/create")
    public ResponseEntity<BaseRes> create(CreateDepartmentReq createDepartmentReq) {
        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("부서 생성 성공")
                .result(departmentService.create(createDepartmentReq))
                .build();
        return ResponseEntity.ok().body(res);
    }
}
