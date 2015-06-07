package com.plataforma.myp7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConfiguracaoController {
	
	@RequestMapping("Configuracao")
	public String salvar(){
		return "ConfiguracaoSalvar";
	}
	
	@RequestMapping("SalvarTema")
	public String salvarTema(String themeselected){
		System.out.println(themeselected);
		return "ConfiguracaoSalvar";
	}
}
