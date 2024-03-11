package com.HelloRolha.HR.feature.position.service;

import com.HelloRolha.HR.feature.position.model.dto.create.CreatePositionReq;
import com.HelloRolha.HR.feature.position.model.dto.create.CreatePositionRes;
import com.HelloRolha.HR.feature.position.model.entity.Position;
import com.HelloRolha.HR.feature.position.repo.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;
    public CreatePositionRes create(CreatePositionReq createPositionReq) {
        Position position = Position.builder()
                .positionName(createPositionReq.getPositionName())
                .positionNum(createPositionReq.getPositionNum())
                .build();
        positionRepository.save(position);
        return CreatePositionRes.builder().id(position.getId()).build();
    }
}
