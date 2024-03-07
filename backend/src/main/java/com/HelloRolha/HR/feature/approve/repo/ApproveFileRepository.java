package com.HelloRolha.HR.feature.approve.repo;

import com.HelloRolha.HR.feature.approve.model.ApproveFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApproveFileRepository extends JpaRepository<ApproveFile, Integer> {
    List<ApproveFile> findAllByApproveId(Integer id);

}
