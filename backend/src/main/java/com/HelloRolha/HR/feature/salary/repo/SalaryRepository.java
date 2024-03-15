package com.HelloRolha.HR.feature.salary.repo;


import com.HelloRolha.HR.feature.salary.model.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
}
