package com.HelloRolha.HR.feature.salary.service;

import com.HelloRolha.HR.feature.commute.service.CommuteService;
import com.HelloRolha.HR.feature.employee.model.dto.EmployeeDto;
import com.HelloRolha.HR.feature.employee.service.EmployeeService;
import com.HelloRolha.HR.feature.goout.service.GooutService;
import com.HelloRolha.HR.feature.overtime.service.OvertimeService;
import com.HelloRolha.HR.feature.salary.model.dto.SalaryDto;
import com.HelloRolha.HR.feature.salary.model.entity.Salary;
import com.HelloRolha.HR.feature.salary.repo.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryService {
    private final EmployeeService employeeService;
    private final CommuteService commuteService;
    private final GooutService gooutService;
    private final OvertimeService overtimeService;

    private final SalaryRepository salaryRepository;

    public List<SalaryDto> createSalary(){
        //ex 3월의 월급을 계산하면
        // 2월달의 근무 내용이 필요하다.
        LocalDate batchDate = LocalDate.now();
        batchDate = batchDate.minusMonths(1);
        LocalDate firstDay= batchDate.withDayOfMonth(1);;
        LocalDate lastDay = batchDate.withDayOfMonth(batchDate.lengthOfMonth());;


        List<SalaryDto> list = getSalaryList(firstDay,lastDay);
        List<Salary> entityList = new ArrayList<>();
        for(SalaryDto salaryDto : list){
            salaryDto.setBatchDate(batchDate);
            entityList.add(salaryDto.toEntity());
        }
        salaryRepository.saveAll(entityList);
        return list;
    }


    // 시작일자에서 종료일자까지 급여 계산
    public List<SalaryDto> getSalaryList(LocalDate startDate, LocalDate endDate){
        List<SalaryDto> salaryDtoList = new ArrayList<>();
        // 전직원 리스트 가져오기
        List<EmployeeDto> employeeList = employeeService.listEmployee();
        // 반복문 //직원마다 총 월급 계산
        for(EmployeeDto employee: employeeList){
            Long commuteCount = commuteService.getWorkTimeByMinutes(startDate,endDate,employee);
            Integer paidVacation = gooutService.getPaidVacationCount(startDate,endDate,employee);
            Long overtime = overtimeService.getOverTime(startDate,endDate,employee);
            salaryDtoList.add(SalaryDto.builder()
                    ///개인 정보
                            .employeeId(employee.getId())
                            .employeeName(employee.getName())
                            .employmentDate(employee.getEmploymentDate())
                            .position(employee.getPosition())
                            .department(employee.getDepartment())
                            .employeeSalary(employee.getSalary())
                    // 출근 일수 // 이 직원의 해당 월의 출근 일 수 가져오기
                            .commuteCount(commuteCount)
                    // 유급 휴가 일수
                            .paidVacationCount(paidVacation)
                    //초과근무 시간
                            .overTime(overtime)
                    //그래서 총 급여
                            .totalSalary(getTotalSalary(startDate,endDate,commuteCount,paidVacation,overtime,employee))
                    .build());

        }
        return salaryDtoList;
    }

    private Long getTotalSalary(LocalDate startDate, LocalDate endDate,Long commuteCount, Integer paidVacation, Long overtime, EmployeeDto employee) {
        // 총 근무 일을 구하자
        Long total = 0L;
        Long salaryHourly = employee.getSalary()/209;

        //Duration duration = Duration.between(startDate,endDate);

        Long workHour = 200L; //Todo 주말 빼는 로직 추가해야함.
        // 주말 빼고 ...
        // 휴무일 빼고 ...
        // 주말 근무는 또 뭐하고...
        // 근무 시간이 충분하지 않다면?

        //근무 시간 + 유급 휴가날 * 8 이 일해야하는 시간보다 적다면?
        if((commuteCount/60) +paidVacation*8 <  workHour){
            // 월급 삭감.
            total += salaryHourly + (commuteCount/60) +paidVacation*8;
        }
        //시급에 맞게 계산
        total += salaryHourly * workHour;

        // 초과 근무 시간에 대해 1.5배의 월급을 준다.
        //employee.getSalary() 는 월급이기 때문에 시간 단위로 해야한다.
        // 하루 8시간 주 40 근로를 한다면, 월 209시간 근무를 한다고 가정한다.
        total += salaryHourly * 15 / 10 * overtime;

        return total;
    }


}
