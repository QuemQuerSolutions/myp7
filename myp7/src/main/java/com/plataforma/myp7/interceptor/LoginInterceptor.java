package com.plataforma.myp7.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {

		String uri = request.getRequestURI();
		if (uri.endsWith("login") || uri.endsWith("efetuaLogin") || uri.contains("resources") || uri.endsWith("cadastroUsuario") || uri.contains("ws"))
			return true;

		if (!Objects.isNull(request.getSession().getAttribute("usuarioLogado")))
			return true;

		response.sendRedirect("login");
		return true;
	}
	
	
}
