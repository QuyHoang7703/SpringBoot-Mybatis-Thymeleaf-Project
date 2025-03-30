package com.example.Spring_MyBatis_Thymeleaf.dto;

import java.time.LocalDate;

public class ProjectRequestDTO {
    private int projectId;
    private String projectNM;
    private String difficulty;
    private int version;
    private LocalDate insTM;
    private LocalDate updTM;
    private int deptId;

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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDate getInsTM() {
        return insTM;
    }

    public void setInsTM(LocalDate insTM) {
        this.insTM = insTM;
    }

    public LocalDate getUpdTM() {
        return updTM;
    }

    public void setUpdTM(LocalDate updTM) {
        this.updTM = updTM;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
}
