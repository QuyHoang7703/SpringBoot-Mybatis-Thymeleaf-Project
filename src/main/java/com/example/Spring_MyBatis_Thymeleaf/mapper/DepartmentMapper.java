package com.example.Spring_MyBatis_Thymeleaf.mapper;

import com.example.Spring_MyBatis_Thymeleaf.model.Department;

import java.util.List;

public interface DepartmentMapper {
    List<Department> findAll();
    Department findById(int id);
}
