package com.example.Spring_MyBatis_Thymeleaf.logic;

import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectRequestDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectResponseDTO;
import com.example.Spring_MyBatis_Thymeleaf.exception.ApplicationException;
import com.example.Spring_MyBatis_Thymeleaf.form.ProjectForm;
import com.example.Spring_MyBatis_Thymeleaf.service.ProjectService;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

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
        if(projectForm.getProjectNM() == null || projectForm.getProjectNM().trim().isEmpty()) {
            bindingResult.rejectValue("difficulty", "error.difficulty", "Difficulty cannot be blank");
        }
        if(projectForm.getDeptId() == 0) {
            bindingResult.rejectValue("deptId", "error.deptId","Must choose a department");
        }

    }

    public boolean checkAvailableProjectName(String projectNM, BindingResult bindingResult) {
        return this.projectService.existsByName(projectNM);
    }

    public ProjectResponseDTO create(ProjectForm projectForm, BindingResult bindingResult) {
        ProjectRequestDTO projectRequestDTO = this.convertToProjectRequestDTO(projectForm);
        if(checkAvailableProjectName(projectRequestDTO.getProjectNM(), bindingResult)){
            bindingResult.rejectValue("projectNM", "error.projectNM", "Project name has already existed");
            return null;
        }
        return this.projectService.create(projectRequestDTO);
    }

    private ProjectRequestDTO convertToProjectRequestDTO(ProjectForm projectForm) {
        ProjectRequestDTO projectRequestDTO = new ProjectRequestDTO();
        projectRequestDTO.setProjectNM(projectForm.getProjectNM());
        projectRequestDTO.setDifficulty(projectForm.getDifficulty());
        projectRequestDTO.setDeptId(projectForm.getDeptId());

        return projectRequestDTO;

    }




}
