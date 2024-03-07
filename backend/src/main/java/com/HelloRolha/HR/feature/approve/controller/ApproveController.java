package com.HelloRolha.HR.feature.approve.controller;

import com.HelloRolha.HR.feature.approve.model.dto.ApproveCreateReq;
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
    public ResponseEntity<ApproveCreateReq> create(@RequestBody ApproveCreateReq approveDto){
        ApproveCreateReq create = approveService.create(approveDto);
        return ResponseEntity.ok().body(create);
    }
}
