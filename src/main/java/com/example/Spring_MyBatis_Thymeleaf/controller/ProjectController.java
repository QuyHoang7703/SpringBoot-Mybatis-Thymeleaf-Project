package com.example.Spring_MyBatis_Thymeleaf.controller;

import com.example.Spring_MyBatis_Thymeleaf.dto.DepartmentDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectResponseDTO;
import com.example.Spring_MyBatis_Thymeleaf.exception.ApplicationException;
import com.example.Spring_MyBatis_Thymeleaf.form.ProjectForm;
import com.example.Spring_MyBatis_Thymeleaf.logic.DepartmentLogic;
import com.example.Spring_MyBatis_Thymeleaf.logic.ProjectLogic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("projects")
public class ProjectController {
    private final ProjectLogic projectLogic;
    private final DepartmentLogic departmentLogic;

    public ProjectController(ProjectLogic projectLogic, DepartmentLogic departmentLogic) {
        this.projectLogic = projectLogic;
        this.departmentLogic = departmentLogic;
    }

    @GetMapping
    public String showListProjects(Model model) {

        return "abc";
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
}
