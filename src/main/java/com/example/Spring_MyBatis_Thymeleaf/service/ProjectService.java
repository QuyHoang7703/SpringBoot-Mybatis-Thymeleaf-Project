package com.example.Spring_MyBatis_Thymeleaf.service;

import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectRequestDTO;
import com.example.Spring_MyBatis_Thymeleaf.dto.ProjectResponseDTO;

public interface ProjectService {
    boolean existsByName(String deptNM);
    ProjectResponseDTO create(ProjectRequestDTO projectRequestDTO);

}
