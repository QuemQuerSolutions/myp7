package com.plataforma.myp7.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.CriptografarBO;
import com.plataforma.myp7.bo.SenhaBO;
import com.plataforma.myp7.dao.UsuarioDAO;
import com.plataforma.myp7.data.ParametroDominio;
import com.plataforma.myp7.data.Usuario;

@Controller
public class LoginController {
	

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session, Model model) { 
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuBanco = usuarioDAO.selecionarPorEmail(usuario.getEmail());
			
			if(usuBanco == null){
				model.addAttribute("mensagemRetorno", "O usuário não existe!");
				throw new Exception();
			}
			
			String senhaHash = CriptografarBO.criptografar(usuario.getSenha());
			
			if(senhaHash.equals(usuBanco.getSenha())){
				session.setAttribute("usuarioLogado", usuBanco);
				return "home";
			}else{
				model.addAttribute("mensagemRetorno", "A senha informada está incorreta!");
				throw new Exception();
			}
		} catch (Exception e) {
			return "login";
		} 
	}
	
	@RequestMapping("/")
	public String loginbarra() { 
		return "redirect:login";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("logout")
	public String mnLogout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
}
