package com.example.Spring_MyBatis_Thymeleaf.service.impl;

import com.example.Spring_MyBatis_Thymeleaf.dto.DepartmentDTO;
import com.example.Spring_MyBatis_Thymeleaf.mapper.DepartmentMapper;
import com.example.Spring_MyBatis_Thymeleaf.model.Department;
import com.example.Spring_MyBatis_Thymeleaf.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = this.departmentMapper.findAll();

        List<DepartmentDTO> departmentDTOS = departments.stream()
                .map(department -> convertToDepartmentDTO(department))
                .toList();

        return departmentDTOS;
    }

    private DepartmentDTO convertToDepartmentDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDeptId(department.getDeptId());
        departmentDTO.setDeptNM(department.getDeptNM());

        return departmentDTO;
    }


}
