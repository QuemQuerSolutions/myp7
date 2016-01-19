package com.plataforma.myp7.bo;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.ThemeEnum;
import com.plataforma.myp7.mapper.UsuarioMapper;

@Service
public class LoginBO{

	private final static Logger log = Logger.getLogger(LoginBO.class);
			
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	private static final String ATTR_THEME = "theme";
	
	public void setUserSession(HttpSession session, String username){
		try {
			Usuario usuBanco = this.usuarioMapper.obterPorEmail(username);
			this.setTheme(session, usuBanco);
			session.setAttribute(ConfigEnum.USUARIO_LOGADO.getValor(), usuBanco);
		} catch (Exception e) {
			log.error("LoginBO.setUserSession", e);
		}
	}
	
	private void setTheme(HttpSession session, Usuario usuBanco){
		if(!Objects.isNull(usuBanco.getTheme()))
			session.setAttribute(ATTR_THEME, ThemeEnum.getValorCSS(usuBanco.getTheme()));
		else
			session.setAttribute(ATTR_THEME, ConfigEnum.THEME_DEFAULT.getValor());
	}
	
}
