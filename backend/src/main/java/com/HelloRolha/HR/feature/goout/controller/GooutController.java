package com.HelloRolha.HR.feature.goout.controller;

import com.HelloRolha.HR.feature.goout.model.Goout;
import com.HelloRolha.HR.feature.goout.model.entity.*;
import com.HelloRolha.HR.feature.goout.service.GooutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/goout")
@CrossOrigin("*")
public class GooutController {
    private final GooutService gooutService;

    public GooutController(GooutService gooutService) {
        this.gooutService = gooutService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(@RequestPart GooutCreateReq postProductReq,
                                 @RequestPart MultipartFile[] uploadFiles) {
        Goout goout = gooutService.create(postProductReq);

        for (MultipartFile uploadFile:uploadFiles) {
            String uploadPath = gooutService.uploadFile(uploadFile);
            gooutService.saveFile(goout.getId(), uploadPath);
        }

        GooutCreateRes response = GooutCreateRes.builder()
                .code(1200)
                .message("휴가/외출 신청 성공")
                .success(true)
                .isSuccess(true)
                .result(GooutCreateResult.builder().id(goout.getId()).build())
                .build();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/check")
    public ResponseEntity<GooutListRes> list() {
        GooutListRes response = gooutService.list();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<GooutReadRes> read(@PathVariable Integer id) {
        GooutReadRes response = gooutService.read(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/return")
    public ResponseEntity<GooutReturnRes> returnStatus(@RequestBody GooutReturnReq gooutReturnReq) {
        gooutService.returnStatus(gooutReturnReq.getId(), gooutReturnReq.getStatus());
        GooutReturnRes response = GooutReturnRes.builder()
                .code(1200)
                .message("휴가/외출 결재 성공")
                .success(true)
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/update")
    public ResponseEntity<GooutUpdateRes> update(@RequestBody GooutUpdateReq gooutUpdateReq) {
        gooutService.update(gooutUpdateReq);
        GooutUpdateRes response = GooutUpdateRes.builder()
                .code(1200)
                .message("휴가/외출 정보 수정 성공")
                .success(true)
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GooutDeleteRes> delete(@PathVariable Integer id) {
        gooutService.delete(id);
        GooutDeleteRes response = GooutDeleteRes.builder()
                .code(1200)
                .message("휴가/외출 정보 삭제 성공")
                .success(true)
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);
    }
}