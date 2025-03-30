package com.example.Spring_MyBatis_Thymeleaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.Spring_MyBatis_Thymeleaf.mapper")
public class SpringMyBatisThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMyBatisThymeleafApplication.class, args);
	}

}
