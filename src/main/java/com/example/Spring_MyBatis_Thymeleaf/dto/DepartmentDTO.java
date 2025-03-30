package com.example.Spring_MyBatis_Thymeleaf.dto;

public class DepartmentDTO {
    private int deptId;
    private String deptNM;

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
