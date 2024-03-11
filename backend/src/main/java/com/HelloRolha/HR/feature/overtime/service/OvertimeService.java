package com.HelloRolha.HR.feature.overtime.service;

import com.HelloRolha.HR.feature.overtime.model.Overtime;
import com.HelloRolha.HR.feature.overtime.model.dto.OvertimeDto;
import com.HelloRolha.HR.feature.overtime.repository.OvertimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OvertimeService {

    private final OvertimeRepository overtimeRepository;

    @Autowired
    public OvertimeService(OvertimeRepository overtimeRepository) {
        this.overtimeRepository = overtimeRepository;
    }

    public Overtime processOvertimeRequest(OvertimeDto overtimeDto) { //초과 근무 신청
        Overtime overtime = Overtime.builder()
                .date(overtimeDto.getDate())
                .shift(overtimeDto.getShift())
                .startTime(overtimeDto.getStartTime())
                .endTime(overtimeDto.getEndTime())
                .reason(overtimeDto.getReason())
                .build();

        return overtimeRepository.save(overtime);
    }

    public void update(OvertimeDto overtimeDto) { //수정
        Optional<Overtime> result = overtimeRepository.findById(overtimeDto.getId());
        if(result.isPresent()) {
            Overtime overtime = result.get();
            overtime.setDate(overtimeDto.getDate());
            overtime.setShift(overtimeDto.getShift());
            overtime.setStartTime(overtimeDto.getStartTime());
            overtime.setEndTime(overtimeDto.getEndTime());
            overtime.setReason(overtimeDto.getReason()); //수정할 수 있는 부분(전체 다 수정가능하게끔)
            overtimeRepository.save(overtime);
        }
    }
}
