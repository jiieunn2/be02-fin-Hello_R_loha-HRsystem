package com.HelloRolha.HR.feature.goout.controller;

import com.HelloRolha.HR.feature.goout.model.Goout;
import com.HelloRolha.HR.feature.goout.model.entity.GooutCreateReq;
import com.HelloRolha.HR.feature.goout.model.entity.GooutCreateRes;
import com.HelloRolha.HR.feature.goout.model.entity.GooutCreateResult;
import com.HelloRolha.HR.feature.goout.service.GooutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/goout")
@CrossOrigin("*")
public class GooutController {
    GooutService gooutService;

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

//    @RequestMapping(method = RequestMethod.GET, value = "/listCeo")
//    public ResponseEntity listCeo() {
//
//        return ResponseEntity.ok().body(productCeoService.listCeo());
//    }
//
//    @GetMapping("/{idx}")
//    public ResponseEntity readCeo(@PathVariable Long idx) {
//        return ResponseEntity.ok().body(productCeoService.readCeo(idx));
//    }
//
//    @RequestMapping(method = RequestMethod.PATCH, value = "/updateCeo")
//    public ResponseEntity updateCeo(@RequestBody ProductCeoUpdateReq productCeoUpdateReq) {
//        productCeoService.updateCeo(productCeoUpdateReq);
//
//        return ResponseEntity.ok().body("수정");
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteCeo")
//    public ResponseEntity deleteCeo(@RequestParam Long idx) {
//        productCeoService.deleteCeo(idx);
//        return ResponseEntity.ok().body("삭제");
//
//    }
}