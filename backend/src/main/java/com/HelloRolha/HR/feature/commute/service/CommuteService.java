package com.HelloRolha.HR.feature.commute.service;


import com.HelloRolha.HR.feature.commute.model.Commute;
import com.HelloRolha.HR.feature.commute.model.dto.CommuteDto;
import com.HelloRolha.HR.feature.commute.repository.CommuteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CommuteService {
    private final CommuteRepository commuteRepository;

    @Autowired
    public CommuteService(CommuteRepository commuteRepository) {
        this.commuteRepository = commuteRepository;
    }

    @Transactional
    public CommuteDto commute() {
        Commute commute = Commute.builder()
                .startTime(LocalDateTime.now())
                .build();

        Commute savedCommute = commuteRepository.save(commute);

        return CommuteDto.builder()
                .id(savedCommute.getId())
                .startTime(savedCommute.getStartTime())
                .build();
    }

    @Transactional
    public CommuteDto leave(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null!");
        }

        Commute commute = commuteRepository.findById(id)
                .orElseThrow(() -> new CommuteNotFoundException(""));

        commute.setEndTime(LocalDateTime.now()); // 현재 시간으로 설정

        // 출근 시간과 퇴근 시간의 Duration 계산
        Duration duration = Duration.between(commute.getStartTime(), commute.getEndTime());

        // 총 업무 시간 계산
        long totalMinutes = duration.toMinutes();
        long hours = totalMinutes / 60; // 시간 계산
        long minutes = totalMinutes % 60; // 분 계산

        // 시간과 분을 문자열로 결합하여 sumTime에 저장
        String sumTime = String.format("%d h %d m", hours, minutes);
        commute.setSumTime(sumTime);

        Commute updatedCommute = commuteRepository.save(commute);

        return CommuteDto.builder()
                .id(updatedCommute.getId())
                .startTime(updatedCommute.getStartTime())
                .endTime(updatedCommute.getEndTime())
                .sumTime(updatedCommute.getSumTime())
                .build();
    }
}
