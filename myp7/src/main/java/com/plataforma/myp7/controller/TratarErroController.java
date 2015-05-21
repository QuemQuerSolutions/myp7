package com.plataforma.myp7.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
