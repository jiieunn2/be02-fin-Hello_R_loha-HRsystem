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
import java.util.List;
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

    public Long getOverTime(LocalDate startDate, LocalDate endDate, EmployeeDto employee) {
        List<Overtime> overtimeList = overtimeRepository.findAllByEmployee(Employee.builder().id(employee.getId()).build());

        //Todo 예외 처리 : 결과가 비어있다면
        Long totalMinutes = 0L;
        for(Overtime overtime:overtimeList){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate overtimeDate = LocalDate.parse(overtime.getDate(), formatter);

            if(overtimeDate.isAfter(startDate) && overtimeDate.isBefore(endDate)){

                LocalTime startTime = LocalTime.parse(overtime.getEndTime());
                LocalTime endTime = LocalTime.parse(overtime.getStartTime());
                // 시작 시간과 종료 시간의 Duration 계산
                Duration duration = Duration.between(startTime, endTime);

                // 총 업무 시간 계산
                totalMinutes += duration.toMinutes();

            }
        }
//        long hours = totalMinutes / 60; // 시간 계산
//        long minutes = totalMinutes % 60; // 분 계산

        // 시간과 분을 문자열로 결합하여 sumTime에 저장
//        String sumTime = String.format("%d h %d m", hours, minutes);
        return totalMinutes / 60;
    }
}
