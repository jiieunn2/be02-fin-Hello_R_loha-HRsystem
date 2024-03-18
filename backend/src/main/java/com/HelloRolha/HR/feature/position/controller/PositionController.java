package com.HelloRolha.HR.feature.position.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.employee.service.EmployeeService;
import com.HelloRolha.HR.feature.position.model.dto.create.CreatePositionReq;
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
    public ResponseEntity<BaseRes> create(CreatePositionReq createPositionReq) {

        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("부서 생성 성공")
                .result(positionService.create(createPositionReq))
                .build();
        return ResponseEntity.ok().body(res);
    }
}
