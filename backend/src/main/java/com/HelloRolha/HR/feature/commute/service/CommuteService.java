package com.HelloRolha.HR.feature.commute.service;


import com.HelloRolha.HR.error.exception.AlreadyCommuteException;
import com.HelloRolha.HR.error.exception.CommuteNotFoundException;
import com.HelloRolha.HR.feature.commute.model.Commute;
import com.HelloRolha.HR.feature.commute.model.dto.CommuteCheckRes;
import com.HelloRolha.HR.feature.commute.model.dto.CommuteDto;
import com.HelloRolha.HR.feature.commute.repository.CommuteRepository;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommuteService {
    private final CommuteRepository commuteRepository;


    @Transactional
    public CommuteDto commute() {
        //자신의 id를 가져오는 법
        Employee employee = ((Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        //오늘 출근을 한 상태라면 못해야한다.
        if(todayCheck(employee)){
            throw AlreadyCommuteException.alreadyCommuteException();
        }

        Commute commute = Commute.builder()
                .employee(employee)
                .build();

        Commute savedCommute = commuteRepository.save(commute);

        return CommuteDto.builder()
                .id(savedCommute.getId())
                .startTime(savedCommute.getCreateAt())
                .build();
    }

    @Transactional
    public CommuteDto leave(Integer id) {


        Commute commute = commuteRepository.findById(id)
                .orElseThrow(() -> new CommuteNotFoundException(""));

//        commute.setEndTime(LocalDateTime.now()); // 현재 시간으로 설정

        // 출근 시간과 퇴근 시간의 Duration 계산
        Duration duration = Duration.between(commute.getCreateAt(), LocalDateTime.now());

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
                .startTime(updatedCommute.getCreateAt())
                .endTime(updatedCommute.getUpdateAt())
                .sumTime(updatedCommute.getSumTime())
                .build();
    }

    public Boolean todayCheck(Employee employee) {

        List<Commute> Commutes = commuteRepository.findAllByEmployee(employee);
        //null일 경우
        if(Commutes.isEmpty()){
            return false;
        }
        Commute lastCommute = Commutes.get(Commutes.size()-1);
//        1.시작 시간이 오늘인지 확인 -- > 어제 날짜면 출근 안함?
        LocalDate today = LocalDate.now();
        LocalDate dateTimeDate = lastCommute.getCreateAt().toLocalDate();

        if(dateTimeDate.equals(today)){
            return true; //오늘 출근 함
        }
        return false; // 오늘 출근 안함.

    }

    public CommuteCheckRes check() {
        Employee employee = ((Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        List<Commute> Commutes = commuteRepository.findAllByEmployee(employee);
        //        null일 경우
        if(Commutes.isEmpty()){
            throw new CommuteNotFoundException("");
        }
        Commute lastCommute = Commutes.get(Commutes.size()-1);
//        1.시작 시간이 오늘인지 확인 -- > 어제 날짜면 출근 안함?
        LocalDate today = LocalDate.now();
        LocalDate dateTimeDate = lastCommute.getCreateAt().toLocalDate();
        Boolean isCommute = false;
        Boolean isLeave = true;
        if(dateTimeDate.equals(today)){
            isCommute = true;
            // 오늘 출근했음.
            if(lastCommute.getSumTime() == null){
                // 출근하고 퇴근은 안한 상태
                isLeave =  false;
                lastCommute.setSumTime("오늘도 화이팅!");
            }
        }


//        2.업데이트 시간이 있는지 확인
//        3. 업데이트 시간이 있으면 퇴큰
//나중에 sql문으로 1개만 가져오게 만들 수 있음.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return CommuteCheckRes.builder()
                .id(lastCommute.getId())
                .isCommute(isCommute)
                .isLeave(isLeave)
                .startTime(lastCommute.getCreateAt().format(formatter))
                .endTime(lastCommute.getUpdateAt().format(formatter))
                .sumTime(lastCommute.getSumTime())
                .build();
    }
}
