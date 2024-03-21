package com.HelloRolha.HR.feature.salary.scheduler;

import com.HelloRolha.HR.feature.salary.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SalaryScheduler {
    private final SalaryService salaryService;
    @Scheduled(cron = "0 55 12 * * *") // 1초에 1000
    public void test () throws IOException {
        System.out.println("test");
        salaryService.createSalary();
    }
}
