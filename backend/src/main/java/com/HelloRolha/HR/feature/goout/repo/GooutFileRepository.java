package com.HelloRolha.HR.feature.goout.repo;


import com.HelloRolha.HR.feature.goout.model.GooutFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface GooutFileRepository  extends JpaRepository<GooutFile, Integer>{
    List<GooutFile> findAllByGooutId(Integer id);
}
