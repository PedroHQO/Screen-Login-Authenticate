package com.br.logginAutenticate.service.autenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LogininteceptorAppConfig implements WebMvcConfigurer {

	@Autowired
	private Logininteceptor logininteceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logininteceptor).excludePathPatterns(
				"/login",
				"/logar",
				"error",
				"/cadastro"
				);
	}
	
}
