package com.HelloRolha.HR.feature.goout.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.goout.model.GooutLine;
import com.HelloRolha.HR.feature.goout.model.dto.*;
import com.HelloRolha.HR.feature.goout.service.GooutLineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gooutLine")
@CrossOrigin("*")
public class GooutLineController {

    private final GooutLineService gooutLineService;

    public GooutLineController(GooutLineService gooutLineService) {
        this.gooutLineService = gooutLineService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<BaseRes> create(@RequestBody GooutLineCreateReq gooutLineCreateReq) {
        GooutLine gooutLine = gooutLineService.create(gooutLineCreateReq);

        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("휴가라인 생성 성공")
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<BaseRes> list() {
        try {
            List<GooutLineList> gooutLineLists = gooutLineService.list();
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재라인 목록 조회 성공")
                    .isSuccess(true)
                    .build();
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            BaseRes response = BaseRes.builder()
                    .code(1400)
                    .message("결재라인 목록 조회 실패: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<BaseRes> read(@PathVariable Integer id) {
        try {
            GooutLineRead gooutLineRead = gooutLineService.read(id);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재라인 상세 조회 성공")
                    .isSuccess(true)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            BaseRes response = BaseRes.builder()
                    .code(1400)
                    .message("결재라인 상세 조회 실패: " + e.getMessage())
                    .isSuccess(false)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }


    @RequestMapping(method = RequestMethod.PATCH, value = "/confirm1")
    public ResponseEntity<BaseRes> confirm1(@RequestBody GooutLineConfirm gooutLineConfirm) {
        try {
            gooutLineService.confirm1(gooutLineConfirm);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재자1의 결재 처리 성공")
                    .isSuccess(true)
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
    public ResponseEntity<BaseRes> confirm2(@RequestBody GooutLineConfirm gooutLineConfirm) {
        try {
            gooutLineService.confirm2(gooutLineConfirm);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재자2의 결재 처리 성공")
                    .isSuccess(true)
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
    public ResponseEntity<BaseRes> reject1(@RequestBody GooutLineConfirm gooutLineConfirm) {
        try {
            gooutLineService.reject1(gooutLineConfirm);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재자1의 거절 처리 성공")
                    .isSuccess(true)
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

    @RequestMapping(method = RequestMethod.POST, value = "/reject2")
    public ResponseEntity<BaseRes> reject2(@RequestBody GooutLineConfirm gooutLineConfirm) {
        try {
            gooutLineService.reject2(gooutLineConfirm);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재자2의 거절 처리 성공")
                    .isSuccess(true)
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<BaseRes> delete(@PathVariable Integer id) {
        try {
            gooutLineService.delete(id);
            BaseRes response = BaseRes.builder()
                    .code(1200)
                    .message("결재라인 삭제 성공")
                    .isSuccess(true)
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            BaseRes response = BaseRes.builder()
                    .code(1400)
                    .message("결재라인 삭제 실패: " + e.getMessage())
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