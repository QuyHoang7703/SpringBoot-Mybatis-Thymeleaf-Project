package com.example.Spring_MyBatis_Thymeleaf.dto;

public class ProjectResponseDTO {
    private int projectId;
    private String projectNM;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectNM() {
        return projectNM;
    }

    public void setProjectNM(String projectNM) {
        this.projectNM = projectNM;
    }
}
