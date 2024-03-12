package com.HelloRolha.HR.feature.approve.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.approve.model.dto.ApproveLine.*;
import com.HelloRolha.HR.feature.approve.service.ApproveLineService;
import com.HelloRolha.HR.feature.goout.model.dto.GooutLineConfirm;
import com.HelloRolha.HR.feature.goout.model.dto.GooutLineList;
import com.HelloRolha.HR.feature.goout.model.dto.GooutLineRead;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<BaseRes> list() {
        try {
            List<ApproveLineList> approveLineLists = approveLineService.list();
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재 라인 목록 조회 성공")
                    .isSuccess(true)
                    .result(approveLineLists)
                    .build();
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            BaseRes response = BaseRes.builder()
                    .code(1400)
                    .message("결재 라인 목록 조회 실패: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<BaseRes> read(@PathVariable Integer id) {
        try {
            ApproveLineRead approveLineRead = approveLineService.read(id);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재 라인 상세 조회 성공")
                    .isSuccess(true)
                    .result(approveLineRead)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            BaseRes response = BaseRes.builder()
                    .code(1400)
                    .message("결재 라인 상세 조회 실패: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }


    @RequestMapping(method = RequestMethod.PATCH, value = "/confirm1")
    public ResponseEntity<BaseRes> confirm1(@RequestBody ApproveLineConfirm approveLineConfirm) {
        try {
            approveLineService.confirm1(approveLineConfirm);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재자1의 결재 처리 성공")
                    .isSuccess(true)
                    .result(approveLineConfirm)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            BaseRes response = BaseRes.builder()
                    .code(1400)
                    .message("결재자1의 결재 처리 실패: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.badRequest().body(response);
        } catch (RuntimeException e) {
            BaseRes response = BaseRes.builder()
                    .code(1500)
                    .message("내부 서버 오류: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/confirm2")
    public ResponseEntity<BaseRes> confirm2(@RequestBody ApproveLineConfirm approveLineConfirm) {
        try {
            approveLineService.confirm2(approveLineConfirm);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재자2의 결재 처리 성공")
                    .isSuccess(true)
                    .result(approveLineConfirm)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            BaseRes response = BaseRes.builder()
                    .code(1400)
                    .message("결재자2의 결재 처리 실패: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.badRequest().body(response);
        } catch (RuntimeException e) {
            BaseRes response = BaseRes.builder()
                    .code(1500)
                    .message("내부 서버 오류: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/reject1")
    public ResponseEntity<BaseRes> reject1(@RequestBody ApproveLineConfirm approveLineConfirm) {
        try {
            approveLineService.reject1(approveLineConfirm);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재자1의 거절 처리 성공")
                    .isSuccess(true)
                    .result(approveLineConfirm)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            BaseRes response = BaseRes.builder()
                    .code(1400)
                    .message("결재자1의 거절 처리 실패: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.badRequest().body(response);
        } catch (RuntimeException e) {
            BaseRes response = BaseRes.builder()
                    .code(1500)
                    .message("내부 서버 오류: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/reject2")
    public ResponseEntity<BaseRes> reject2(@RequestBody ApproveLineConfirm approveLineConfirm) {
        try {
            approveLineService.reject2(approveLineConfirm);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재자2의 거절 처리 성공")
                    .isSuccess(true)
                    .result(approveLineConfirm)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            BaseRes response = BaseRes.builder()
                    .code(1400)
                    .message("결재자2의 거절 처리 실패: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.badRequest().body(response);
        } catch (RuntimeException e) {
            BaseRes response = BaseRes.builder()
                    .code(1500)
                    .message("내부 서버 오류: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity<BaseRes> delete(@PathVariable Integer id) {
        try {
            approveLineService.delete(id);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재 라인 삭제 성공")
                    .isSuccess(true)
                    .result("삭제한 id : " + id)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            BaseRes response = BaseRes.builder()
                    .code(1400)
                    .message("결재 라인 삭제 실패: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.badRequest().body(response);
        } catch (RuntimeException e) {
            BaseRes response = BaseRes.builder()
                    .code(1500)
                    .message("내부 서버 오류: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}