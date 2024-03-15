package com.HelloRolha.HR.feature.commute.controller;
import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.commute.model.dto.CommuteDto;
import com.HelloRolha.HR.feature.commute.service.CommuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/commute/check")
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

    @GetMapping("/commute/mainlist") // 출퇴근 목록 조회
    public ResponseEntity<BaseRes> mainlist() {
        List<CommuteDto> commuteDtos = commuteService.mainlist(); // 서비스 계층의 mainlist 메서드 호출
        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("출퇴근 현황 성공")
                .isSuccess(true)
                .result(commuteDtos)
                .build();
        return ResponseEntity.ok().body(response);
    }
}

