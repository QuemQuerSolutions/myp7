package com.plataforma.myp7.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.data.Usuario;

@Controller
public class LoginController {
	

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session , HttpServletRequest req) { 
		
		if ("1".equals("1"))
		{
			session.setAttribute("usuarioLogado", usuario);
			return "home";
		}
				
		return "login";
	}
	
	@RequestMapping("/")
	public String loginbarra(Model model, HttpServletRequest req) { 
		return "redirect:login";
	}
	
	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest req) {
		return "login";
	}
	
	@RequestMapping("logout")
	public String mnLogout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
}
