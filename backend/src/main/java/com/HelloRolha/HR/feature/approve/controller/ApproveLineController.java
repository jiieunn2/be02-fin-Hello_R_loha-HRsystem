package com.HelloRolha.HR.feature.approve.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.approve.model.ApproveLine;
import com.HelloRolha.HR.feature.approve.model.dto.ApproveCreateReq;
import com.HelloRolha.HR.feature.approve.model.dto.ApproveLine.ApproveLineCreateReq;
import com.HelloRolha.HR.feature.approve.model.dto.ApproveLine.ApproveLinePatchReq;
import com.HelloRolha.HR.feature.approve.service.ApproveLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/approve/line")
@RequiredArgsConstructor
public class ApproveLineController {
    private final ApproveLineService approveLineService;
    @PostMapping("/create")
    public ResponseEntity<BaseRes> create(ApproveLineCreateReq approveLineCreateReq) {
        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("결재 라인 생성 성공")
                .result(approveLineService.create(approveLineCreateReq))
                .build();

        return ResponseEntity.ok().body(res);
    }

    @PatchMapping("/patch")
    public ResponseEntity<BaseRes> create(ApproveLinePatchReq approveLinePatchReq) {
        BaseRes res = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("결재 라인 변경 성공")
                .result(approveLineService.patch(approveLinePatchReq))
                .build();

        return ResponseEntity.ok().body(res);
    }
}
