package com.example.Spring_MyBatis_Thymeleaf.mapper;

import com.example.Spring_MyBatis_Thymeleaf.model.Project;

public interface ProjectMapper {
    int countByProjectName (String deptNM);
    void createProject(Project project);
}
