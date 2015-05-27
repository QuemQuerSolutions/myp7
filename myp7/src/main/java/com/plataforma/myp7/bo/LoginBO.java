package com.plataforma.myp7.bo;

import static com.plataforma.myp7.Util.Utils.setMsgRetorno;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.plataforma.myp7.dao.UsuarioDAO;
import com.plataforma.myp7.data.Usuario;

public class LoginBO {

	private UsuarioDAO usuarioDAO;
	
	public LoginBO(){
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public String getDestinoLogin(Usuario usuario, HttpSession session,	Model model) {
		try {
			Usuario usuBanco = this.usuarioDAO.selecionarPorEmail(usuario.getEmail());
			
			if(Objects.isNull(usuBanco)){
				setMsgRetorno(model, "O usuário não existe!");
				return "login";
			}
			
			if(!CriptografarBO.criptografar(usuario.getSenha()).equals(usuBanco.getSenha())){
				setMsgRetorno(model, "A senha informada está incorreta!");
				return "login";
			}
			
			session.setAttribute("usuarioLogado", usuBanco);
			return "home";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "login";
		}
	}
}
