package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.plataforma.myp7.dao.UsuarioDAO;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.GeralEnum;
import com.plataforma.myp7.enums.ThemeEnum;

public class LoginBO {

	private UsuarioDAO usuarioDAO;
	private static final String ATTR_THEME = "theme";
	
	public LoginBO(){
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public String getDestinoLogin(Usuario usuario, HttpSession session,	Model model) {
		try {
			Usuario usuBanco = new Usuario(); 
			usuBanco = this.usuarioDAO.selecionarPorEmail(usuario.getEmail());
			
			if(Objects.isNull(usuBanco)){
				setMsgRetorno(model, "O usu�rio n�o existe!");
				return "components/login";
			}
			
			if(!CriptografarBO.criptografar(usuario.getSenha()).equals(usuBanco.getSenha())){
				setMsgRetorno(model, "A senha informada est� incorreta!");
				return "components/login";
			}
			
			this.setTheme(session, usuBanco);
			
			session.setAttribute("usuarioLogado", usuBanco);
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
			session.setAttribute(ATTR_THEME, GeralEnum.THEME_DEFAULT.getValor());
	}
}
