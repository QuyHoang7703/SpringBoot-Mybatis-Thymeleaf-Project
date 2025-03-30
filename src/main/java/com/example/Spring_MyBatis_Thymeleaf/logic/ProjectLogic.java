package com.example.Spring_MyBatis_Thymeleaf.logic;

import com.example.Spring_MyBatis_Thymeleaf.dto.PaginationDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectRequestDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectResponseDTO;
import com.example.Spring_MyBatis_Thymeleaf.form.ProjectForm;
import com.example.Spring_MyBatis_Thymeleaf.service.ProjectService;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

@Component
public class ProjectLogic {
    private final ProjectService projectService;


    public ProjectLogic(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void validateInputProject(ProjectForm projectForm, BindingResult bindingResult) {
        if(projectForm.getProjectNM() == null || projectForm.getProjectNM().trim().isEmpty()) {
            bindingResult.rejectValue("projectNM", "error.projectNM", "Project name cannot be blank");
        }
        if(projectForm.getDifficulty() == null || projectForm.getDifficulty().trim().isEmpty()) {
            bindingResult.rejectValue("difficulty", "error.difficulty", "Difficulty cannot be blank");
        }
        if(projectForm.getDeptId() == 0) {
            bindingResult.rejectValue("deptId", "error.deptId","Must choose a department");
        }

    }

    public ProjectResponseDTO getProjectById(int projectId) {
        return this.projectService.getProjectById(projectId);
    }

    public ProjectResponseDTO create(ProjectForm projectForm, BindingResult bindingResult) {
        ProjectRequestDTO projectRequestDTO = this.convertToProjectRequestDTO(projectForm);
        if(checkAvailableProjectNameBeforeInsert(projectRequestDTO.getProjectNM())){
            bindingResult.rejectValue("projectNM", "error.projectNM", "Project name has already existed");
            return null;
        }
        return this.projectService.create(projectRequestDTO);
    }

    public ProjectResponseDTO update(ProjectForm projectForm, BindingResult bindingResult) {
        ProjectRequestDTO projectRequestDTO = this.convertToProjectRequestDTO(projectForm);
        if(checkAvailableProjectNameBeforeUpdate(projectRequestDTO.getProjectNM(), projectRequestDTO.getProjectId())){
            bindingResult.rejectValue("projectNM", "error.projectNM", "Project name has already existed");
            return null;
        }
        return this.projectService.update(projectRequestDTO);
    }

    public PaginationDTO<List<ProjectResponseDTO>> getProjectWithPagination(int currentPage, int pageSize, String keySearch) {
        PaginationDTO<List<ProjectResponseDTO>> paginationDTO = this.projectService.getAllProjectsWithPagination(currentPage, pageSize, keySearch);
        return paginationDTO;
    }

    private ProjectRequestDTO convertToProjectRequestDTO(ProjectForm projectForm) {
        ProjectRequestDTO projectRequestDTO = new ProjectRequestDTO();
        projectRequestDTO.setProjectId(projectForm.getProjectId());
        projectRequestDTO.setProjectNM(projectForm.getProjectNM());
        projectRequestDTO.setDifficulty(projectForm.getDifficulty());
        projectRequestDTO.setDeptId(projectForm.getDeptId());

        return projectRequestDTO;

    }

    private boolean checkAvailableProjectNameBeforeInsert(String projectNM) {
        return this.projectService.existsByName(projectNM);
    }

    private boolean checkAvailableProjectNameBeforeUpdate(String projectNM, int projectId) {
        return this.projectService.checkProjectNameBeforeUpdate(projectNM, projectId);
    }






}
