package com.plataforma.myp7.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.plataforma.myp7.bo.LoginBO;

@Controller
public class LoginController {
	
	@Autowired
	private LoginBO loginBO;

	@RequestMapping(value={"/", "/home**"}, method = RequestMethod.GET)
	public String home(Model model, HttpSession session){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			this.loginBO.setUserSession(session, userDetail.getUsername());
			return "components/home";
		}
		return "components/login";
	}

	@RequestMapping("/login")
	public String login(String erro , String logout) {
		return "components/login";
	}
	
}
