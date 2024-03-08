package com.HelloRolha.HR.feature.commute.repository;


import com.HelloRolha.HR.feature.commute.model.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuteRepository extends JpaRepository<Commute, Integer> {
}
