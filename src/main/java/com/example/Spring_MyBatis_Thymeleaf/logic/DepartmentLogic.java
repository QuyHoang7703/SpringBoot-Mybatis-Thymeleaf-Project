package com.example.Spring_MyBatis_Thymeleaf.logic;

import com.example.Spring_MyBatis_Thymeleaf.dto.DepartmentDTO;
import com.example.Spring_MyBatis_Thymeleaf.service.DepartmentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentLogic {
    private final DepartmentService departmentService;

    public DepartmentLogic(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentDTO> departmentDTOS = this.departmentService.getAllDepartments();
        return departmentDTOS;
    }


}
