package com.example.Spring_MyBatis_Thymeleaf.controller;

import com.example.Spring_MyBatis_Thymeleaf.dto.DepartmentDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.PaginationDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectResponseDTO;
import com.example.Spring_MyBatis_Thymeleaf.exception.ApplicationException;
import com.example.Spring_MyBatis_Thymeleaf.form.ProjectForm;
import com.example.Spring_MyBatis_Thymeleaf.logic.DepartmentLogic;
import com.example.Spring_MyBatis_Thymeleaf.logic.ProjectLogic;
import com.example.Spring_MyBatis_Thymeleaf.model.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("projects")
public class ProjectController {
    private final ProjectLogic projectLogic;
    private final DepartmentLogic departmentLogic;
    @Value("${pagination.pageSize}")
    private int pageSize;

    public ProjectController(ProjectLogic projectLogic, DepartmentLogic departmentLogic) {
        this.projectLogic = projectLogic;
        this.departmentLogic = departmentLogic;
    }

    @GetMapping
    public String showListProjects(@RequestParam(name="currentPage", defaultValue = "1") int currentPage,
                                   @RequestParam(name="keySearch", defaultValue = "") String keySearch,
                                   Model model) {
        PaginationDTO<List<ProjectResponseDTO>> paginationDTO = this.projectLogic.getProjectWithPagination(currentPage, pageSize, keySearch.trim());
        model.addAttribute("paginationDTO", paginationDTO);
        model.addAttribute("keySearch", keySearch);
        return "projects/project-list";
    }

    @GetMapping("/add-form")
    public String showAddForm(Model model) {
        ProjectForm projectForm = new ProjectForm();
        List<DepartmentDTO> departmentDTOS = this.departmentLogic.getAllDepartments();

        model.addAttribute("projectForm", projectForm);
        model.addAttribute("departmentDTOS", departmentDTOS);
        return "projects/add-project";
    }

    @PostMapping("/add-confirm")
    public String confirmAddProject(@ModelAttribute("projectForm") ProjectForm projectForm,
                                    BindingResult bindingResult,
                                    Model model) {

        projectLogic.validateInputProject(projectForm, bindingResult);
        if (bindingResult.hasErrors()) {
            List<DepartmentDTO> departmentDTOS = this.departmentLogic.getAllDepartments();
            model.addAttribute("departmentDTOS", departmentDTOS);
            model.addAttribute("projectForm", projectForm);
            return "projects/add-project";
        }
        model.addAttribute("projectForm", projectForm);

        return "projects/confirm-add-project";
    }

    @PostMapping("add")
    public String saveProject(@ModelAttribute("projectForm") ProjectForm projectForm,
                              BindingResult bindingResult,
                              Model model) {
        ProjectResponseDTO projectResponseDTO = projectLogic.create(projectForm, bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("projectForm", projectForm);
            return "projects/confirm-add-project";
        }
        model.addAttribute("projectResponseDTO", projectResponseDTO);
        return "projects/success-add-project";
    }

    @PostMapping("add/back")
    public String backConfirmAddProject(@ModelAttribute("projectForm") ProjectForm projectForm,
                                        Model model) {
        List<DepartmentDTO> departmentDTOS = this.departmentLogic.getAllDepartments();

        model.addAttribute("projectForm", projectForm);
        model.addAttribute("departmentDTOS", departmentDTOS);

        return "projects/add-project";
    }

    @GetMapping("/detail/{projectId}")
    public String showDetail(@PathVariable("projectId") int projectId, Model model) {
        ProjectResponseDTO projectResponseDTO = this.projectLogic.getProjectById(projectId);
        List<DepartmentDTO> departmentDTOS = this.departmentLogic.getAllDepartments();
        model.addAttribute("projectResponseDTO", projectResponseDTO);
        model.addAttribute("departmentDTOS", departmentDTOS);
        return "projects/detail-project";
    }

    @PostMapping("/update-confirm")
    public String confirmUpdateProject(@ModelAttribute ProjectForm projectForm,
                                    BindingResult bindingResult,
                                    Model model) {

        projectLogic.validateInputProject(projectForm, bindingResult);
        if (bindingResult.hasErrors()) {
            List<DepartmentDTO> departmentDTOS = this.departmentLogic.getAllDepartments();
            model.addAttribute("departmentDTOS", departmentDTOS);
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("projectResponseDTO", projectForm);
            return "projects/detail-project";
        }
        model.addAttribute("projectForm", projectForm);

        return "projects/confirm-update-project";
    }

    @PostMapping("update/back")
    public String backConfirmUpdateProject(@ModelAttribute("projectForm") ProjectForm projectForm,
                                        Model model) {
        List<DepartmentDTO> departmentDTOS = this.departmentLogic.getAllDepartments();

        model.addAttribute("projectResponseDTO", projectForm);
        model.addAttribute("departmentDTOS", departmentDTOS);

        return "projects/detail-project";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute ProjectForm projectForm,
                                BindingResult bindingResult,
                                Model model) {
        ProjectResponseDTO projectResponseDTO = projectLogic.update(projectForm, bindingResult);
        if(bindingResult.hasErrors()) {
            List<DepartmentDTO> departmentDTOS = this.departmentLogic.getAllDepartments();
            model.addAttribute("departmentDTOS", departmentDTOS);
            model.addAttribute("projectResponseDTO", projectForm);
            return "projects/confirm-update-project";
        }

        model.addAttribute("projectResponseDTO", projectResponseDTO);
        return "projects/success-update-project";
    }
}
