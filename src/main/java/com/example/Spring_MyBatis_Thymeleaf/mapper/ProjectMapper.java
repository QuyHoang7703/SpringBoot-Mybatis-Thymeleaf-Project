package com.example.Spring_MyBatis_Thymeleaf.mapper;

import com.example.Spring_MyBatis_Thymeleaf.model.Project;

import java.util.List;

public interface ProjectMapper {
    int countByProjectName (String deptNM);
    void createProject(Project project);
    List<Project> getAllProjects(int pageSize, int offset, String keySearch);
    int countProjectsByKeySearch(String keySearch);
    Project getProjectById(int id);
    void updateProject(Project project);
    int countByProjectNameBeforeUpdate(String projectNM, int projectId);
    void deleteProjectById(int projectId);
}
