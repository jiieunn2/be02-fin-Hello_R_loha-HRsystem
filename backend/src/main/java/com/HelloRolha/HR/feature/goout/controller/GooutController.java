package com.HelloRolha.HR.feature.goout.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.goout.model.Goout;
import com.HelloRolha.HR.feature.goout.model.dto.*;
import com.HelloRolha.HR.feature.goout.service.GooutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/goout")
@CrossOrigin("*")
public class GooutController {
    private final GooutService gooutService;

    public GooutController(GooutService gooutService) {
        this.gooutService = gooutService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(@RequestPart GooutCreateReq gooutCreateReq,
                                 @RequestPart(name = "uploadFiles", required = false) MultipartFile[] uploadFiles) {
        Goout goout = gooutService.create(gooutCreateReq);
        if (uploadFiles != null) {
            for (MultipartFile uploadFile : uploadFiles) {
                String uploadPath = gooutService.uploadFile(uploadFile);
                gooutService.saveFile(goout.getId(), uploadPath);
            }
        }

        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("휴가/외출 신청 성공")
                .isSuccess(true)
                .result(goout.getId())
                .build();
        return ResponseEntity.ok().body(response);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/check")
    public ResponseEntity<BaseRes> list() {
        List<GooutList> gooutLists = gooutService.list();
        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("휴가/외출 확인 성공")
                .isSuccess(true)
                .result(gooutLists)
                .build();
        return ResponseEntity.ok(response);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/check/{id}")
    public ResponseEntity<BaseRes> read(@PathVariable Integer id) {
        GooutRead gooutRead = gooutService.read(id); // 서비스에서 GooutRead 객체 받기
        if (gooutRead == null) {
            return ResponseEntity.notFound().build(); // 적절한 예외 처리
        }

        BaseRes response = BaseRes.builder() // 응답 객체 생성
                .code(1200)
                .message("휴가/외출 상세 확인 성공")
                .isSuccess(true)
                .result(gooutRead)
                .build();
        return ResponseEntity.ok(response);
    }


    @RequestMapping(method = RequestMethod.PATCH, value = "/return")
    public ResponseEntity<BaseRes> returnStatus(@RequestBody GooutReturnReq gooutReturnReq) {
        gooutService.returnStatus(gooutReturnReq);
        String message;

        if (gooutReturnReq.getStatus() == 1) {
            message = "결재자1 휴가/외출 승인 성공";
        } else if (gooutReturnReq.getStatus() == 2) {
            message = "결재자2 휴가/외출 승인 성공";
        } else if (gooutReturnReq.getStatus() == 3) {
            message = "휴가/외출 반려 성공";
        } else {
            message = "잘못된 상태 값";
        }

        BaseRes response = BaseRes.builder()
                .code(1200)
                .message(message)
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);
    }


    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity<BaseRes> update(@RequestBody GooutUpdateReq gooutUpdateReq) {
        gooutService.update(gooutUpdateReq);
        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("휴가/외출 정보 수정 성공")
                .isSuccess(true)
                .result(gooutUpdateReq)
                .build();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity<BaseRes> delete(@PathVariable Integer id) {
        gooutService.delete(id);
        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("휴가/외출 정보 삭제 성공")
                .isSuccess(true)
                .result("삭제한 id : " + id)
                .build();
        return ResponseEntity.ok(response);
    }
}