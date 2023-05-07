package com.example.test.blog.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).
		select().paths(PathSelectors.ant("/api/**")).
		apis(RequestHandlerSelectors.basePackage("com.example.test.blog")).
		build().apiInfo(apiInfo());
		
	}
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Post Controller Api",//title
				
				"This is used to Multiple comments"	,//description
				
				"version-3.8", //version
				
						"Terms and Service"		, //termsOfServiceUrl
				
				new Contact("Jts", "WWW.JTS.COM", "JTS@gmail.com"), //contactName
				
				"The Licence has aproved", //license
				
			"URL LICENCE",Collections.EMPTY_LIST);	//licenseUrl)
		
		
	}
	
	
	
	
	
	
	
	
}
