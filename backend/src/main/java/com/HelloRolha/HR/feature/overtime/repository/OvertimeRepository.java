package com.HelloRolha.HR.feature.overtime.repository;


import com.HelloRolha.HR.feature.overtime.model.Overtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {
}
