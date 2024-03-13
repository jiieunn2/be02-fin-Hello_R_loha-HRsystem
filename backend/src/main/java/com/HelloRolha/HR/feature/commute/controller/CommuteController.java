package com.HelloRolha.HR.feature.commute.controller;
import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.commute.model.dto.CommuteDto;
import com.HelloRolha.HR.feature.commute.service.CommuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class CommuteController {

    private final CommuteService commuteService;

    public CommuteController(CommuteService commuteService) {
        this.commuteService = commuteService;
    }

    @PostMapping("/commute")
    public ResponseEntity<BaseRes> commute() {

        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("출근 성공")
                .result(commuteService.commute())
                .build();

        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/check")
    public ResponseEntity<BaseRes> check() {

        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("출근 확인 성공")
                .result(commuteService.check())
                .build();
        return ResponseEntity.ok().body(res);
    }
    @PatchMapping("/leave/{id}")
    public ResponseEntity<BaseRes> leave(@PathVariable Integer id) {

        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("출근 확인 성공")
                .result(commuteService.leave(id))
                .build();
        return ResponseEntity.ok().body(res);
    }
}

