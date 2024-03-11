package com.HelloRolha.HR.feature.department.service;

import com.HelloRolha.HR.feature.department.model.dto.CreateDepartmentReq;
import com.HelloRolha.HR.feature.department.model.dto.CreateDepartmentRes;
import com.HelloRolha.HR.feature.department.model.entity.Department;
import com.HelloRolha.HR.feature.department.repo.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public CreateDepartmentRes create(CreateDepartmentReq createDepartmentReq) {
        Department department = Department.builder()
                .departmentNum(createDepartmentReq.getDepartmentNum())
                .departmentName(createDepartmentReq.getDepartmentName())
                .build();
        departmentRepository.save(department);

        return CreateDepartmentRes.builder()
                .id(department.getId())
                .build();
    }
}
