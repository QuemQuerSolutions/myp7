package com.plataforma.myp7.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.LoginBO;
import com.plataforma.myp7.data.Usuario;

@Controller
public class LoginController {
	
	@Autowired
	private LoginBO loginBO;

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session, Model model) { 
		return this.loginBO.getDestinoLogin(usuario, session, model); 
	}

	@RequestMapping("/")
	public String loginbarra() { 
		return "redirect:login";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "components/login";
	}
	
	@RequestMapping("logout")
	public String mnLogout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
}
