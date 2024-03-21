package com.HelloRolha.HR.feature.manager.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.employee.model.dto.SignUp.SignUpReq;
import com.HelloRolha.HR.feature.employee.service.EmployeeService;
import com.HelloRolha.HR.feature.manager.service.ManagerService;
import com.HelloRolha.HR.feature.salary.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin("*")
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    private final SalaryService salaryService;

    @RequestMapping(method = RequestMethod.PATCH, value = "/authorize/{employeeId}")
    public ResponseEntity authorize(@PathVariable Integer employeeId) {

        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("승인 성공")
                .result(managerService.authorize(employeeId))
                .build();


        return ResponseEntity.ok().body(res);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/authorize")
    public ResponseEntity findAuthorize() {

        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("findAuthorize 성공")
                .result(managerService.findAuthorize())
                .build();


        return ResponseEntity.ok().body(res);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employee")
    public ResponseEntity<BaseRes> listEmployee() {

        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("모든 직원 목록 가져오기 성공")
                .result(managerService.listEmployee())
                .build();


        return ResponseEntity.ok().body(res);
    }

    //직원 상세보기
    @RequestMapping(method = RequestMethod.GET, value = "/employee/{employeeId}")
    public ResponseEntity readEmployee(@PathVariable Integer employeeId) {

        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("직원 정보 성공")
                .result(managerService.readEmployee(employeeId))
                .build();


        return ResponseEntity.ok().body(res);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/salary")
    public ResponseEntity salary() {
        LocalDate startDate =LocalDate.parse("2024-01-01");
        LocalDate endDate =LocalDate.parse("2024-01-31");
        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("직원 정보 성공")
                .result(salaryService.createSalary())
                .build();


        return ResponseEntity.ok().body(res);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/attend")
//    public ResponseEntity<BaseRes> listCommute() {
//
//        BaseRes res = BaseRes.builder()
//                .code(200)
//                .isSuccess(true)
//                .message("모든 출퇴근 목록 가져오기 성공")
//                .result(managerService.listCommute())
//                .build();
//
//
//        return ResponseEntity.ok().body(res);
//    }

    // Todo 오늘 것만 가져오기 sql문을 복잡하게 써서 할까?
//    @RequestMapping(method = RequestMethod.GET, value = "/attend/check")
//    public ResponseEntity<BaseRes> checkToday() {
//
//        BaseRes res = BaseRes.builder()
//                .code(200)
//                .isSuccess(true)
//                .message("모든 출퇴근 목록 가져오기 성공")
//                .result(managerService.checkToday())
//                .build();
//
//
//        return ResponseEntity.ok().body(res);
//    }
}
