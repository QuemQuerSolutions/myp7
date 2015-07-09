package com.plataforma.myp7.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.UsuarioBO;

@Controller
public class ConfiguracaoController {
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@RequestMapping("Configuracao")
	public String salvar(){
		return "ConfiguracaoSalvar";
	}
	
	@RequestMapping("SalvarTema")
	public String salvarTema(Model model, HttpSession session, String themeselected){
		this.usuarioBO.updateTheme(themeselected, model, session);
		return "ConfiguracaoSalvar";
	}
}
