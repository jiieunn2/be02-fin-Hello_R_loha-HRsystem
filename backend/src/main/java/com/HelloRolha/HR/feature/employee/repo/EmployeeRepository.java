package com.HelloRolha.HR.feature.employee.repo;

import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
}
