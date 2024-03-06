package com.HelloRolha.HR.feature.department.repo;

import com.HelloRolha.HR.feature.department.model.entity.Department;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
