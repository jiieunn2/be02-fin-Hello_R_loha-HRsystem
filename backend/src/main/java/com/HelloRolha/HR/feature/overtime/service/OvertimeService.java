package com.HelloRolha.HR.feature.overtime.service;

import com.HelloRolha.HR.feature.employee.model.dto.EmployeeDto;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.overtime.model.Overtime;
import com.HelloRolha.HR.feature.overtime.model.dto.OvertimeDto;
import com.HelloRolha.HR.feature.overtime.repository.OvertimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OvertimeService {

    private final OvertimeRepository overtimeRepository;

    @Autowired
    public OvertimeService(OvertimeRepository overtimeRepository) {
        this.overtimeRepository = overtimeRepository;
    }

    public Overtime processOvertimeRequest(OvertimeDto overtimeDto) {
        Overtime overtime = Overtime.builder()
                .date(overtimeDto.getDate())
                .shift(overtimeDto.getShift())
                .startTime(overtimeDto.getStartTime())
                .endTime(overtimeDto.getEndTime())
                .reason(overtimeDto.getReason())
                .status("대기 중")
                .build();

        return overtimeRepository.save(overtime);
    }

    public List<OvertimeDto> list() {
        List<Overtime> overtimes = overtimeRepository.findAll();
        List<OvertimeDto> overtimeDtos = new ArrayList<>();

        for (Overtime overtime : overtimes) {
            if (overtime != null) {
                OvertimeDto overtimeDto = OvertimeDto.builder()
                        .id(overtime.getId())
                        .shift(overtime.getShift())
                        .startTime(overtime.getStartTime())
                        .endTime(overtime.getEndTime())
                        .date(overtime.getDate())
                        .reason(overtime.getReason())
                        .status(overtime.getStatus())
                        .build();
                overtimeDtos.add(overtimeDto);
            }
        }
        return overtimeDtos;
    }

    public OvertimeDto read(Integer id) {
        Overtime overtime = overtimeRepository.findById(id).orElseThrow(()->new OvertimeNotFoundException(""));

        return OvertimeDto.builder()
                .id(overtime.getId())
                .shift(overtime.getShift())
                .startTime(overtime.getStartTime())
                .endTime(overtime.getEndTime())
                .date(overtime.getDate())
                .reason(overtime.getReason())
                .status(overtime.getStatus())
                .build();
    }


    public void update(Integer id, OvertimeDto overtimeDto) {
        Optional<Overtime> result = overtimeRepository.findById(id);
        if(result.isPresent()) {
            Overtime overtime = result.get();
            overtime.setShift(overtimeDto.getShift());
            overtime.setDate(overtimeDto.getDate());
            overtime.setStartTime(overtimeDto.getStartTime());
            overtime.setEndTime(overtimeDto.getEndTime());
            overtime.setReason(overtimeDto.getReason());
            overtime.setStatus("대기 중");
            overtimeRepository.save(overtime);
        }
    }

    public void approveOvertime(Integer id) {
        Overtime overtime = overtimeRepository.findById(id)
                .orElseThrow(() -> new OvertimeNotFoundException("초과 근무를 찾을 수 없습니다."));

        // 초과 근무 승인 시 상태를 "승인됨"으로 변경합니다.
        overtime.setStatus("승인됨");
        overtimeRepository.save(overtime);
    }

}
