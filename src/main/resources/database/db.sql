CREATE DATABASE db_mybatis_thymeleaf;
USE db_mybatis_thymeleaf;
CREATE TABLE department (
                            deptId INT AUTO_INCREMENT PRIMARY KEY,
                            deptNM VARCHAR(255) NOT NULL
);

CREATE TABLE project (
                         projectId INT AUTO_INCREMENT PRIMARY KEY,
                         projectNM VARCHAR(255) NOT NULL,
                         difficulty VARCHAR(50),
                         insTM DATE,
                         updTM DATE,
                         deptId INT,
                         version INT,
                         FOREIGN KEY (deptId) REFERENCES department(deptId)
);

CREATE TABLE roles (
                       roleId INT AUTO_INCREMENT PRIMARY KEY,
                       roleName VARCHAR(30)
);

CREATE TABLE users (
                       username VARCHAR(50) PRIMARY KEY,
                       password VARCHAR(100) NOT NULL,
                       roleId INT NOT NULL,
                       CONSTRAINT FK_User_Role FOREIGN KEY(roleId) REFERENCES Roles(roleId)
);


