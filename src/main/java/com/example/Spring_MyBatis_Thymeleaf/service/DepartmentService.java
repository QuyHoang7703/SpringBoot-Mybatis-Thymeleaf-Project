package com.example.Spring_MyBatis_Thymeleaf.service;

import com.example.Spring_MyBatis_Thymeleaf.dto.DepartmentDTO;
import com.example.Spring_MyBatis_Thymeleaf.model.Department;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAllDepartments();
}
