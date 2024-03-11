package com.HelloRolha.HR.feature.overtime.controller;

import com.HelloRolha.HR.feature.overtime.model.Overtime;
import com.HelloRolha.HR.feature.overtime.model.dto.OvertimeDto;
import com.HelloRolha.HR.feature.overtime.service.OvertimeNotFoundException;
import com.HelloRolha.HR.feature.overtime.service.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class OvertimeController {


    private final OvertimeService overtimeService;

    @Autowired
    public OvertimeController(OvertimeService overtimeService) {
        this.overtimeService = overtimeService;
    }

    @PostMapping("/overtime/create") //초과 근무 신청
    public ResponseEntity<Overtime> submitOvertimeRequest(OvertimeDto overtimeDto) {
        Overtime savedOvertime = overtimeService.processOvertimeRequest(overtimeDto);
        return ResponseEntity.ok(savedOvertime);
    }

    @PatchMapping("/overtime/update/{id}") //초과 근무 수정
    public ResponseEntity<String> update(OvertimeDto overtimeDto) { //form-data 형식
        try {
            overtimeService.update(overtimeDto);
            return ResponseEntity.ok().body("연장 근무가 성공적으로 수정되었습니다.");
        } catch (OvertimeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
