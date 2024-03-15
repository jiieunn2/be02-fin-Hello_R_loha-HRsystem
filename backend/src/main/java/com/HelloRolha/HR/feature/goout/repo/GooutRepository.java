package com.HelloRolha.HR.feature.goout.repo;

import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import com.HelloRolha.HR.feature.goout.model.Goout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GooutRepository extends JpaRepository<Goout, Integer> {
    List<Goout> findAllByEmployee(Employee employee);
}
