package com.HelloRolha.HR.feature.approve.repo;

import com.HelloRolha.HR.feature.approve.model.Approve;
import com.HelloRolha.HR.feature.approve.model.ApproveFile;
import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApproveFileRepository extends JpaRepository<ApproveFile, Integer> {
    List<ApproveFile> findAllByApproveId(Integer approve_id);
    void deleteByApprove(Approve approve);


}
