package com.HelloRolha.HR.feature.approve.repo;

import com.HelloRolha.HR.feature.approve.model.Approve;
import com.HelloRolha.HR.feature.approve.model.ApproveLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApproveLineRepository extends JpaRepository<ApproveLine, Integer> {
    //List<ApproveLine> findByApproveOrderByOrderAsc(Approve approve);
}
