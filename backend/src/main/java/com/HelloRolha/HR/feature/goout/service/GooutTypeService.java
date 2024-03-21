package com.HelloRolha.HR.feature.goout.service;

import com.HelloRolha.HR.feature.goout.model.GooutType;
import com.HelloRolha.HR.feature.goout.model.dto.*;
import com.HelloRolha.HR.feature.goout.repo.GooutTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GooutTypeService {
    private final GooutTypeRepository gooutTypeRepository;
    public GooutType create(GooutTypeCreateReq gooutTypeCreateReq) {
        GooutType gooutType = GooutType.builder()
                .name(gooutTypeCreateReq.getName())
                .detail(gooutTypeCreateReq.getDetail())
                .maxHoliday(gooutTypeCreateReq.getMaxHoliday())
                .build();

        return gooutTypeRepository.save(gooutType);
    }

    public List<GooutTypeList> list() {
        List<GooutType> gooutTypes = gooutTypeRepository.findAll(); // 모든 게시글 조회
        List<GooutTypeList> gooutTypeLists = new ArrayList<>();

        for (GooutType gooutType : gooutTypes) {
            GooutTypeList gooutTypeList = GooutTypeList.builder()
                    .id(gooutType.getId())
                    .name(gooutType.getName())
                    .build();
            gooutTypeLists.add(gooutTypeList);
        }
        return gooutTypeLists;
    }

    public GooutTypeRead read(Integer id) {
        GooutType gooutType = gooutTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 GooutType이 존재하지 않습니다."));

        return GooutTypeRead.builder()
                .name(gooutType.getName())
                .detail(gooutType.getDetail())
                .maxHoliday(gooutType.getMaxHoliday())
                .build();
    }

    public GooutType update(GooutTypeUpdateReq gooutTypeUpdateReq) {
        GooutType gooutType = gooutTypeRepository.findById(gooutTypeUpdateReq.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 GooutType이 존재하지 않습니다."));

        gooutType.setName(gooutTypeUpdateReq.getName());
        gooutType.setDetail(gooutTypeUpdateReq.getDetail());
        gooutType.setMaxHoliday(gooutTypeUpdateReq.getMaxHoliday());

        return gooutTypeRepository.save(gooutType);
    }

    public void delete(Integer id) {
        GooutType gooutType = gooutTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 GooutType이 존재하지 않습니다."));

        gooutTypeRepository.delete(gooutType);
    }

}
