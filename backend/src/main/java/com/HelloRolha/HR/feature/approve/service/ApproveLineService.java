package com.HelloRolha.HR.feature.approve.service;

import com.HelloRolha.HR.error.ApproveNotFoundException;
import com.HelloRolha.HR.error.EntityNotFoundException;
import com.HelloRolha.HR.feature.approve.model.Approve;
import com.HelloRolha.HR.feature.approve.model.ApproveLine;
import com.HelloRolha.HR.feature.approve.model.dto.ApproveLine.ApproveLineCreateReq;
import com.HelloRolha.HR.feature.approve.model.dto.ApproveLine.ApproveLinePatchReq;
import com.HelloRolha.HR.feature.approve.repo.ApproveLineRepository;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApproveLineService {
    private final ApproveLineRepository approveLineRepository;
    public Object create(ApproveLineCreateReq approveLineCreateReq) {
        ApproveLine approveLine =approveLineRepository.save(ApproveLine.builder()
                        .approve(Approve.builder().id(approveLineCreateReq.getApproveId()).build())
                        .comment(approveLineCreateReq.getComment())
                        .confirmer(Employee.builder().id(approveLineCreateReq.getConfirmerId()).build())
                        .status(0)
                .build());

        return null;
    }

    public Object applyApprove(Integer approveId) {
        Optional<ApproveLine> optional = approveLineRepository.findById(approveId);
        if(optional.isEmpty()){
            throw ApproveNotFoundException.forIdx(approveId);
        }
        Employee employee = ((Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ApproveLine approveLine = optional.get();
        if (approveLine.getConfirmer().getId().equals(employee.getId())){
            approveLine.setApproveTime(LocalDateTime.now());
            approveLine.setStatus(1);
        }
        approveLineRepository.save(approveLine);


        return approveLine;
    }


    public Object patch(ApproveLinePatchReq approveLinePatchReq) {
        return null;
    }
}
