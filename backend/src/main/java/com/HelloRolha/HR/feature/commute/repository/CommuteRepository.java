package com.HelloRolha.HR.feature.commute.repository;


import com.HelloRolha.HR.feature.commute.model.Commute;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommuteRepository extends JpaRepository<Commute, Integer> {
    List<Commute> findAllByEmployee(Employee employee);
}
