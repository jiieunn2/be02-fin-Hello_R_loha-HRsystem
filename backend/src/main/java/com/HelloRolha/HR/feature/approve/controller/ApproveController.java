package com.HelloRolha.HR.feature.approve.controller;

import com.HelloRolha.HR.feature.approve.repo.ApproveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/approve")
@RequiredArgsConstructor
public class ApproveController {
    private ApproveRepository approveRepository;
}
