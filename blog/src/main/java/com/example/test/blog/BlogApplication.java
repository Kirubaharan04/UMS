package com.example.test.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BlogApplication {

	
	@Bean
	public  ModelMapper mapper() {
		return new ModelMapper();
	} 
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		
		
		
		
	}

}
