package com.HelloRolha.HR.feature.department.service;

import com.HelloRolha.HR.error.DepartmentNotFoundException;
import com.HelloRolha.HR.feature.department.model.dto.ReadDepartmentRes;
import com.HelloRolha.HR.feature.department.model.dto.create.CreateDepartmentReq;
import com.HelloRolha.HR.feature.department.model.dto.create.CreateDepartmentRes;
import com.HelloRolha.HR.feature.department.model.dto.patch.PatchDepartmentReq;
import com.HelloRolha.HR.feature.department.model.dto.patch.PatchDepartmentRes;
import com.HelloRolha.HR.feature.department.model.entity.Department;
import com.HelloRolha.HR.feature.department.repo.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ReadDepartmentRes read(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(departmentOptional.isEmpty()){
            throw DepartmentNotFoundException.forIdx(id);
        }
        Department department = departmentOptional.get();
        return ReadDepartmentRes.builder()
                .id(department.getId())
                .departmentName(department.getDepartmentName())
                .departmentNum(department.getDepartmentNum())
                .build();
    }
    public List<ReadDepartmentRes> list() {
        List<ReadDepartmentRes> departmentResList = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();
        for (Department department:departments){
            departmentResList.add(ReadDepartmentRes.builder()
                            .id(department.getId())
                            .departmentNum(department.getDepartmentNum())
                            .departmentName(department.getDepartmentName())
                    .build());
        }
        return departmentResList;
    }


    public PatchDepartmentRes patch(PatchDepartmentReq patchDepartmentReq) {
        Optional<Department> departmentOptional = departmentRepository.findById(patchDepartmentReq.getId());
        if(departmentOptional.isEmpty()){
            throw DepartmentNotFoundException.forIdx(patchDepartmentReq.getId());
        }
        Department department = departmentOptional.get();
        department.setDepartmentName(patchDepartmentReq.getDepartmentName());
        department.setDepartmentNum(patchDepartmentReq.getDepartmentNum());
        departmentRepository.save(department);

        return PatchDepartmentRes.builder()
                .id(department.getId())
                .departmentName(department.getDepartmentName())
                .departmentNum(department.getDepartmentNum())
                .build();
    }

    public Boolean delete(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(departmentOptional.isEmpty()){
            throw DepartmentNotFoundException.forIdx(id);
        }

        departmentRepository.deleteById(id);

        return true;
    }
}
