package com.HelloRolha.HR.feature.goout.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.goout.model.GooutType;
import com.HelloRolha.HR.feature.goout.model.dto.*;
import com.HelloRolha.HR.feature.goout.service.GooutTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gooutType")
@CrossOrigin("*")
public class GooutTypeController {
    private final GooutTypeService gooutTypeService;

    public GooutTypeController(GooutTypeService gooutTypeService) {
        this.gooutTypeService = gooutTypeService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<BaseRes> create(@RequestBody GooutTypeCreateReq gooutTypeCreateReq) {
        GooutType gooutType = gooutTypeService.create(gooutTypeCreateReq);

        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("휴가타입 생성 성공")
                .isSuccess(true)
                .result(gooutTypeCreateReq)
                .build();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<BaseRes> list() {
        List<GooutTypeList> gooutTypeLists = gooutTypeService.list();

        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("휴가타입 list 읽기 성공")
                .isSuccess(true)
                .result(gooutTypeLists)
                .build();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<BaseRes> read(@PathVariable Integer id) {
        GooutTypeRead gooutTypeRead = gooutTypeService.read(id);

        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("휴가타입 detail 읽기 성공")
                .isSuccess(true)
                .result(gooutTypeRead)
                .build();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity<BaseRes> update(@RequestBody GooutTypeUpdateReq gooutTypeUpdateReq) {
        GooutType updatedGooutType = gooutTypeService.update(gooutTypeUpdateReq);

        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("휴가타입 수정 성공")
                .isSuccess(true)
                .result(gooutTypeUpdateReq)
                .build();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity<BaseRes> delete(@PathVariable Integer id) {
        gooutTypeService.delete(id);

        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("휴가타입 삭제 성공")
                .isSuccess(true)
                .result("삭제한 id : " + id)
                .build();
        return ResponseEntity.ok(response);
    }
}
