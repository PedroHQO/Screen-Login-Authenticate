package com.br.logginAutenticate.service.autenticator;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.br.logginAutenticate.service.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class Logininteceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest resquest, HttpServletResponse response, Object handler) throws IOException {
		
		if(CookieService.getCookie(resquest, "usuarioId") != null) {
			return true;
		}
		
		response.sendRedirect("/login");
		return false;
	}
	
}
