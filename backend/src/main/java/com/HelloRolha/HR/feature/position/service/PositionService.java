package com.HelloRolha.HR.feature.position.service;

import com.HelloRolha.HR.feature.position.model.entity.Position;
import com.HelloRolha.HR.feature.position.repo.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;
    public void create(Integer positionNum, String positionName) {
        positionRepository.save(Position.builder()
                        .positionNum(positionNum)
                        .positionName(positionName)
                .build());
    }
}
