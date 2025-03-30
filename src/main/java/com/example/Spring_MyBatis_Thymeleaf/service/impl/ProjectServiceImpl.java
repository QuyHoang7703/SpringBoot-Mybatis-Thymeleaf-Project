package com.example.Spring_MyBatis_Thymeleaf.service.impl;

import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectRequestDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectResponseDTO;
import com.example.Spring_MyBatis_Thymeleaf.mapper.DepartmentMapper;
import com.example.Spring_MyBatis_Thymeleaf.mapper.ProjectMapper;
import com.example.Spring_MyBatis_Thymeleaf.model.Department;
import com.example.Spring_MyBatis_Thymeleaf.model.Project;
import com.example.Spring_MyBatis_Thymeleaf.service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;
    private final DepartmentMapper departmentMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper, DepartmentMapper departmentMapper) {
        this.projectMapper = projectMapper;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public boolean existsByName(String projectNM) {
        int count = projectMapper.countByProjectName(projectNM);
        return count > 0;
    }

    @Override
    public ProjectResponseDTO create(ProjectRequestDTO projectRequestDTO) {
        Project project = new Project();
        project.setProjectName(projectRequestDTO.getProjectNM());
        project.setDifficulty(projectRequestDTO.getDifficulty());
        project.setVersion(1);
        project.setInsTM(LocalDate.now());
        project.setDeptId(projectRequestDTO.getDeptId());
        this.projectMapper.createProject(project);

        return this.convertToProjectResponseDTO(project);
    }

    private ProjectResponseDTO convertToProjectResponseDTO(Project project) {
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        projectResponseDTO.setProjectId(project.getProjectId());
        projectResponseDTO.setProjectNM(project.getProjectName());
        return projectResponseDTO;
    }
}
