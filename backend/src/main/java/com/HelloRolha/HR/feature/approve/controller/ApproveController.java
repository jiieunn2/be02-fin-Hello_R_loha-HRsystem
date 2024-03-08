package com.HelloRolha.HR.feature.approve.controller;

import com.HelloRolha.HR.feature.approve.model.dto.*;
import com.HelloRolha.HR.feature.approve.service.ApproveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approve")
@RequiredArgsConstructor
public class ApproveController {
    private final ApproveService approveService;

    @PostMapping("/create")
    public ResponseEntity<ApproveCreateRes> create(@RequestBody ApproveCreateReq approveCreateReq) {
        ApproveCreateRes createRes = approveService.create(approveCreateReq);
        return ResponseEntity.ok().body(createRes);
    }
    @GetMapping("/list")
    public ResponseEntity<ApproveListRes> list() {
        ApproveListRes list = approveService.list(); // 서비스 메소드 호출
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ApproveReadRes> read(@PathVariable Integer approveId) {
        ApproveReadRes readRes = approveService.read(approveId);
        return ResponseEntity.ok().body(readRes);
    }

    @PutMapping("/update")
    public ResponseEntity<ApproveUpdateRes> update(@PathVariable Integer approveId,
                                                          @RequestBody ApproveUpdate approveUpdate) {
        ApproveUpdateRes updateRes = approveService.update(approveId, approveUpdate);
        return ResponseEntity.ok().body(updateRes);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer approveId) {
        approveService.delete(approveId);
        return ResponseEntity.noContent().build();
    }
}
