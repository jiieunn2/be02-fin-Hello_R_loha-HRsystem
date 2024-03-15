package com.HelloRolha.HR.feature.overtime.repository;


import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.overtime.model.Overtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {
    List<Overtime> findAllByEmployee(Employee employee);
}
