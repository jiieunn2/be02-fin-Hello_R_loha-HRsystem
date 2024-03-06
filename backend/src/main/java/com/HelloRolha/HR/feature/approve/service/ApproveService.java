package com.HelloRolha.HR.feature.approve.service;

import com.HelloRolha.HR.feature.approve.controller.ApproveController;
import com.HelloRolha.HR.feature.approve.repo.ApproveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApproveService {
    private final ApproveRepository approveRepository;
}
