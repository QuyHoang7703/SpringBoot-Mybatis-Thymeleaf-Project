package com.example.Spring_MyBatis_Thymeleaf.service.impl;

import com.example.Spring_MyBatis_Thymeleaf.dto.PaginationDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectRequestDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectResponseDTO;
import com.example.Spring_MyBatis_Thymeleaf.mapper.DepartmentMapper;
import com.example.Spring_MyBatis_Thymeleaf.mapper.ProjectMapper;
import com.example.Spring_MyBatis_Thymeleaf.model.Department;
import com.example.Spring_MyBatis_Thymeleaf.model.Project;
import com.example.Spring_MyBatis_Thymeleaf.service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        Department department = this.departmentMapper.findById(projectRequestDTO.getDeptId());
        project.setDepartment(department);
        this.projectMapper.createProject(project);

        return this.convertToProjectResponseDTO(project);
    }

    @Override
    public PaginationDTO<List<ProjectResponseDTO>> getAllProjectsWithPagination(int currentPage, int pageSize, String keySearch) {
        int offset = (currentPage - 1) * pageSize;
        String formattedKeySearch = '%' + keySearch + '%';

        List<Project> projects = this.projectMapper.getAllProjects(pageSize, offset, formattedKeySearch);
        List<ProjectResponseDTO> projectResponseDTOS = projects.stream().map(this::convertToProjectResponseDTO).toList();

        int countOfProjectWithKeySearch = this.projectMapper.countProjectsByKeySearch(formattedKeySearch);
        int totalPages = (int) Math.ceil((double) countOfProjectWithKeySearch/pageSize);

        PaginationDTO<List<ProjectResponseDTO>> paginationDTO = new PaginationDTO<>();
        paginationDTO.setCurrentPage(currentPage);
        paginationDTO.setContent(projectResponseDTOS);
        paginationDTO.setPageSize(totalPages);

        return paginationDTO;
    }

    @Override
    public ProjectResponseDTO getProjectById(int projectId) {
        Project project = this.projectMapper.getProjectById(projectId);
        return this.convertToProjectResponseDTO(project);
    }

    @Override
    public ProjectResponseDTO update(ProjectRequestDTO projectRequestDTO) {
        Project project = this.projectMapper.getProjectById(projectRequestDTO.getProjectId());
        project.setProjectName(projectRequestDTO.getProjectNM());
        project.setDifficulty(projectRequestDTO.getDifficulty());
        project.setVersion(project.getVersion() + 1);
        project.setUpdTM(LocalDate.now());
        Department department = this.departmentMapper.findById(projectRequestDTO.getDeptId());
        project.setDepartment(department);
        this.projectMapper.updateProject(project);

        return this.convertToProjectResponseDTO(project);
    }

    @Override
    public boolean checkProjectNameBeforeUpdate(String projectNM, int projectId) {
        int countByProjectName = this.projectMapper.countByProjectNameBeforeUpdate(projectNM, projectId);
        return countByProjectName > 0;
    }

    @Override
    public void deleteProjectById(int projectId) {
        this.projectMapper.deleteProjectById(projectId);
    }


    private ProjectResponseDTO convertToProjectResponseDTO(Project project) {
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        projectResponseDTO.setProjectId(project.getProjectId());
        projectResponseDTO.setProjectNM(project.getProjectName());
        projectResponseDTO.setDifficulty(project.getDifficulty());
        projectResponseDTO.setVersion(project.getVersion());
        projectResponseDTO.setDeptId(project.getDepartment().getDeptId());
        projectResponseDTO.setDeptNM(project.getDepartment().getDeptNM());
        projectResponseDTO.setInsTM(project.getInsTM());
        projectResponseDTO.setUpdTM(project.getUpdTM());
        return projectResponseDTO;
    }
}
