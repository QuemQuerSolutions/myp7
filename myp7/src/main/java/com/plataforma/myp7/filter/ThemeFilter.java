package com.plataforma.myp7.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.GeralEnum;
import com.plataforma.myp7.enums.ThemeEnum;

public class ThemeFilter implements Filter {

	private static final String ATTR_USUARIO = "usuarioLogado";
	private static final String ATTR_THEME = "theme";
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		Object usuerLogado = session.getAttribute(ATTR_USUARIO);
		Usuario usuario = null;
		
		if(!Objects.isNull(usuerLogado)){
			usuario = (Usuario) usuerLogado;
			
			if(Objects.isNull(usuario.getTheme()))
				session.setAttribute(ATTR_THEME, GeralEnum.THEME_DEFAULT.getValor());
			else
				session.setAttribute(ATTR_THEME, ThemeEnum.getValorCSS(usuario.getTheme()));
		}
		
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	@Override
	public void destroy() {}

}
