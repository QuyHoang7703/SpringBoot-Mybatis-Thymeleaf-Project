package com.example.Spring_MyBatis_Thymeleaf.form;

public class ProjectForm {
    private int projectId;
    private String projectNM;
    private String difficulty;
    private int deptId;
    private String deptNM;

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

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptNM() {
        return deptNM;
    }

    public void setDeptNM(String deptNM) {
        this.deptNM = deptNM;
    }


}
