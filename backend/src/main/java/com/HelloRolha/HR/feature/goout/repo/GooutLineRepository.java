package com.HelloRolha.HR.feature.goout.repo;

import com.HelloRolha.HR.feature.goout.model.Goout;
import com.HelloRolha.HR.feature.goout.model.GooutLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GooutLineRepository extends JpaRepository<GooutLine, Integer> {
}
