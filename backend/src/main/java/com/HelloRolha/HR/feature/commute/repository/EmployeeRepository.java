package com.HelloRolha.HR.feature.commute.repository;


import com.HelloRolha.HR.feature.commute.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
