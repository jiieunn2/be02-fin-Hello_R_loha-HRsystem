package com.HelloRolha.HR.feature.goout.service;

import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.employee.repo.EmployeeRepository;
import com.HelloRolha.HR.feature.goout.model.Goout;
import com.HelloRolha.HR.feature.goout.model.GooutLine;
import com.HelloRolha.HR.feature.goout.model.dto.*;
import com.HelloRolha.HR.feature.goout.repo.GooutLineRepository;
import com.HelloRolha.HR.feature.goout.repo.GooutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GooutLineService {
    private final GooutLineRepository gooutLineRepository;
    private final EmployeeRepository employeeRepository;
    private final GooutRepository gooutRepository;
    ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    LocalDateTime localDateTimeInKorea = nowInKorea.toLocalDateTime();

    @Transactional
    public GooutLine create(GooutLineCreateReq gooutLineCreateReq) {
        if (gooutLineCreateReq.getConfirmer1Id().equals(gooutLineCreateReq.getConfirmer2Id())) {
            throw new IllegalArgumentException("결재자1의 ID와 결재자2의 ID는 같을 수 없습니다.");
        }

        Employee confirmer1 = employeeRepository.findById(gooutLineCreateReq.getConfirmer1Id())
                .orElseThrow(() -> new IllegalArgumentException("결재자1의 ID가 존재하지 않습니다."));
        Employee confirmer2 = employeeRepository.findById(gooutLineCreateReq.getConfirmer2Id())
                .orElseThrow(() -> new IllegalArgumentException("결재자2의 ID가 존재하지 않습니다."));
        Goout goout = gooutRepository.findById(gooutLineCreateReq.getGooutId())
                .orElseThrow(() -> new IllegalArgumentException("휴가의 ID가 존재하지 않습니다."));

        GooutLine gooutLine = GooutLine.builder()
                .confirmer1(confirmer1)
                .confirmer2(confirmer2)
                .goout(goout)
                .approveTime(localDateTimeInKorea)
                .status(0)
                .build();

        return gooutLineRepository.save(gooutLine);
    }

    public List<GooutLineList> list() {
        List<GooutLine> gooutLines = gooutLineRepository.findAll(); // 모든 결재라인 조회
        List<GooutLineList> gooutLineLists = new ArrayList<>();

        for (GooutLine line : gooutLines) {
            Employee confirmer1 = line.getConfirmer1();
            if (confirmer1 == null) {
                throw new RuntimeException("confirmer1의 정보를 찾을 수 없습니다.");
            }

            Employee confirmer2 = line.getConfirmer2();
            if (confirmer2 == null) {
                throw new RuntimeException("confirmer2의 정보를 찾을 수 없습니다.");
            }

            GooutLineList gooutLineList = GooutLineList.builder()
                    .id(line.getId())
                    .confirmer1Name(confirmer1.getName())
                    .confirmer2Name(confirmer2.getName())
                    .build();
            gooutLineLists.add(gooutLineList);
        }
        return gooutLineLists;
    }

    public GooutLineRead read(Integer id) {
        GooutLine gooutLine = gooutLineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 결재라인이 존재하지 않습니다."));

        return GooutLineRead.builder()
                .confirmer1Id(gooutLine.getConfirmer1().getId())
                .confirmer2Id(gooutLine.getConfirmer2().getId())
                .gooutId(gooutLine.getGoout().getId())
                .comment(gooutLine.getComment())
                .approveTime(gooutLine.getApproveTime())
                .applyTime(gooutLine.getApplyTime())
                .status(gooutLine.getStatus())
                .build();
    }


    public void confirm1(GooutLineConfirm gooutLineConfirm) {
        GooutLine gooutLine = gooutLineRepository.findById(gooutLineConfirm.getGooutId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 결재라인이 존재하지 않습니다."));

        if (!gooutLine.getConfirmer1().getId().equals(gooutLineConfirm.getConfirmer1Id())){
            throw new IllegalArgumentException("결재자1이 올바르지 않습니다.");
        }

        if (gooutLine.getStatus() != 0) {
            throw new IllegalArgumentException("결재자1이 결재를 할 수 있는 상태가 아닙니다.");
        }

        gooutLine.setStatus(1);

        gooutLine.setApplyTime(localDateTimeInKorea); // applyTime 설정
        gooutLineRepository.save(gooutLine); // 변경 사항 저장

    }

    public void confirm2(GooutLineConfirm gooutLineConfirm) {
        GooutLine gooutLine = gooutLineRepository.findById(gooutLineConfirm.getGooutId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 결재라인이 존재하지 않습니다."));

        if (!gooutLine.getConfirmer2().getId().equals(gooutLineConfirm.getConfirmer2Id())){
            throw new IllegalArgumentException("결재자2가 올바르지 않습니다.");
        }

        if (gooutLine.getStatus() != 1) {
            throw new IllegalArgumentException("결재자2가 결재를 할 수 있는 상태가 아닙니다.");
        }

        gooutLine.setStatus(2);

        gooutLine.setApplyTime(localDateTimeInKorea); // applyTime 설정
        gooutLineRepository.save(gooutLine); // 변경 사항 저장
    }

    public void reject1 (GooutLineConfirm gooutLineConfirm) {
        GooutLine gooutLine = gooutLineRepository.findById(gooutLineConfirm.getGooutId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 결재라인이 존재하지 않습니다."));

        if (!gooutLine.getConfirmer1().getId().equals(gooutLineConfirm.getConfirmer1Id())){
            throw new IllegalArgumentException("결재자1이 올바르지 않습니다.");
        }

        if (gooutLine.getStatus() != 0) {
            throw new IllegalArgumentException("결재자1이 결재를 할 수 있는 상태가 아닙니다.");
        }

        gooutLine.setStatus(3);

        gooutLine.setComment(gooutLineConfirm.getComment());

        gooutLine.setApplyTime(localDateTimeInKorea); // applyTime 설정
        gooutLineRepository.save(gooutLine); // 변경 사항 저장
    }

    public void reject2 (GooutLineConfirm gooutLineConfirm) {
        GooutLine gooutLine = gooutLineRepository.findById(gooutLineConfirm.getGooutId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 결재라인이 존재하지 않습니다."));

        if (!gooutLine.getConfirmer2().getId().equals(gooutLineConfirm.getConfirmer2Id())){
            throw new IllegalArgumentException("결재자2가 올바르지 않습니다.");
        }

        if (gooutLine.getStatus() != 1) {
            throw new IllegalArgumentException("결재자2가 결재를 할 수 있는 상태가 아닙니다.");
        }

        gooutLine.setStatus(3);

        gooutLine.setComment(gooutLineConfirm.getComment());

        gooutLine.setApplyTime(localDateTimeInKorea); // applyTime 설정
        gooutLineRepository.save(gooutLine); // 변경 사항 저장
    }


    public void delete(Integer id) {
        GooutLine gooutLine = gooutLineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 결재라인이 존재하지 않습니다."));

        gooutLineRepository.delete(gooutLine);
    }

}
