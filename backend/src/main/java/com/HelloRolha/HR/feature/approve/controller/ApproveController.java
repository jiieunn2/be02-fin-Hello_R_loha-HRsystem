package com.HelloRolha.HR.feature.approve.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.approve.model.dto.*;
import com.HelloRolha.HR.feature.approve.service.ApproveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/approve")
@RequiredArgsConstructor
public class ApproveController {
    private final ApproveService approveService;

    @PostMapping("/create")
    public ResponseEntity<BaseRes> create(@RequestBody ApproveCreateReq approveCreateReq) {
        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("결재 문서 생성 성공")
                .result(approveService.create(approveCreateReq))
                .build();

        return ResponseEntity.ok().body(res);
    }
    @GetMapping("/list")
    public ResponseEntity<BaseRes> list() {
        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("결재 목록 성공")
                .result(approveService.list())
                .build();

        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<BaseRes> read(@PathVariable Integer approveId) {
        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("결재 목록 성공")
                .result(approveService.read(approveId))
                .build();

        return ResponseEntity.ok().body(res);
    }

    @PutMapping("/update")
    public ResponseEntity<BaseRes> update(@PathVariable Integer approveId,
                                                          @RequestBody ApproveUpdate approveUpdate) {
        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("결재 목록 성공")
                .result(approveService.update(approveId, approveUpdate))
                .build();

        return ResponseEntity.ok().body(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseRes> delete(@PathVariable Integer approveId) {
        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("결재 목록 성공")
                .result(approveService.delete(approveId))
                .build();

        return ResponseEntity.ok().body(res);
    }
}
