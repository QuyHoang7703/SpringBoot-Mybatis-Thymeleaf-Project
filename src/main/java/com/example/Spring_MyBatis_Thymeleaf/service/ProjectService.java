package com.example.Spring_MyBatis_Thymeleaf.service;

import com.example.Spring_MyBatis_Thymeleaf.dto.PaginationDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectRequestDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectResponseDTO;

import java.util.List;

public interface ProjectService {
    boolean existsByName(String deptNM);
    ProjectResponseDTO create(ProjectRequestDTO projectRequestDTO);
    PaginationDTO<List<ProjectResponseDTO>> getAllProjectsWithPagination(int currentPage, int pageSize, String keySearch);
    ProjectResponseDTO getProjectById(int projectId);
    ProjectResponseDTO update(ProjectRequestDTO projectRequestDTO);
    boolean checkProjectNameBeforeUpdate(String projectNM, int projectId);
}
