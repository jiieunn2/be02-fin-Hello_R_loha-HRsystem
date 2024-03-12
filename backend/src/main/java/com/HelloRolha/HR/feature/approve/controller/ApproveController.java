package com.HelloRolha.HR.feature.approve.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.approve.model.Approve;
import com.HelloRolha.HR.feature.approve.model.dto.Approve.*;
import com.HelloRolha.HR.feature.approve.service.ApproveService;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.goout.model.dto.GooutList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/approve")
@RequiredArgsConstructor
public class ApproveController {
    private final ApproveService approveService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(@RequestPart ApproveCreateReq approveCreateReq,
                                @RequestPart MultipartFile[] uploadFiles) {
ApproveCreateRes approveCreateRes = approveService.create(approveCreateReq);
        for (MultipartFile uploadFile:uploadFiles) {
            String uploadPath = approveService.uploadFile(uploadFile);
            approveService.saveFile(approveCreateRes.getId(), uploadPath);
        }

        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("결재 생성")
                .isSuccess(true)
                .result(approveCreateRes)
                .build();
        return ResponseEntity.ok().body(response);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<BaseRes> list() {
        List<ApproveList> approveLists = approveService.list();
        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("결재 목록 확인 성공")
                .isSuccess(true)
                .result(approveLists)
                .build();
        return ResponseEntity.ok(response);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/read/{id}")
    public ResponseEntity<ApproveRead> read(@PathVariable Integer id) {
        ApproveRead readRes = approveService.read(id);
        return ResponseEntity.ok().body(readRes);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity<BaseRes> update(@RequestBody ApproveUpdate approveUpdate) {
        approveService.update(approveUpdate);
        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("결재 수정 성공")
                .isSuccess(true)
                .result(approveUpdate)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/return")
    public ResponseEntity<BaseRes> returnStatus(@RequestBody ApproveReturn approveReturn) {
        approveService.returnStatus(approveReturn.getId(), approveReturn.getApproveLineId());
        String message;

        if (approveReturn.getApproveLineId() == 1) {
            message = "결재자1 결재 승인 성공";
        } else if (approveReturn.getApproveLineId() == 2) {
            message = "결재자2 결재 승인 성공";
        } else if (approveReturn.getApproveLineId() == 3) {
            message = "결재 반려 성공";
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity<BaseRes> delete(@PathVariable Integer id) {
        approveService.delete(id);
        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("결재 삭제 성공")
                .isSuccess(true)
                .build();
        return ResponseEntity.ok().body(response);
    }
}
