package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.ThemeEnum;
import com.plataforma.myp7.enums.TipoUsuarioEnum;
import com.plataforma.myp7.mapper.UsuarioMapper;

@Service
public class LoginBO {

	@Autowired
	private UsuarioMapper usuarioMapper;
	
	private static final String ATTR_THEME = "theme";
	private static final String TIPO_USUARIO = "tipoUsuarioRetorno";
	
	
	public String getDestinoLogin(Usuario usuario, HttpSession session,	Model model) {
		try {
			Usuario usuBanco = new Usuario(); 
			usuBanco = this.usuarioMapper.obterPorEmail(usuario.getEmail());
			
			if(Objects.isNull(usuBanco)){
				setMsgRetorno(model, "O usuário não existe!");
				return "components/login";
			}
			
			if(!CriptografarBO.criptografar(usuario.getSenha()).equals(usuBanco.getSenha())){
				setMsgRetorno(model, "A senha informada estï¿½ incorreta!");
				return "components/login";
			}
			this.setTipoUsuarioSession(session, usuBanco);
			
			this.setTheme(session, usuBanco);
			
			session.setAttribute(ConfigEnum.USUARIO_LOGADO.getValor(), usuBanco);
			return "components/home";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "components/login";
		}
	}
	
	private void setTheme(HttpSession session, Usuario usuBanco){
		if(!Objects.isNull(usuBanco.getTheme()))
			session.setAttribute(ATTR_THEME, ThemeEnum.getValorCSS(usuBanco.getTheme()));
		else
			session.setAttribute(ATTR_THEME, ConfigEnum.THEME_DEFAULT.getValor());
	}
	
	private void setTipoUsuarioSession(HttpSession session, Usuario usuBanco){
		if(!Objects.isNull(usuBanco.getTipoUsuario()))
			session.setAttribute(TIPO_USUARIO,usuBanco.getTipoUsuario().equalsIgnoreCase(TipoUsuarioEnum.PORTAL.getSiglaUsuario().toString())?TipoUsuarioEnum.PORTAL.getSiglaUsuario().toString():TipoUsuarioEnum.RETAGUARDA.getSiglaUsuario().toString());
	}
}
