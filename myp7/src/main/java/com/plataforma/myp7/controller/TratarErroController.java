package com.plataforma.myp7.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TratarErroController {
	
	@RequestMapping("404")
	public String execute404(Model model, HttpServletRequest req) {
		return "err/404"; 
	}
	
	@RequestMapping("500")
	public String execute500() {
		return "err/500"; 
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("username", userDetail.getUsername());
		}

		return "err/403";
	}
}
