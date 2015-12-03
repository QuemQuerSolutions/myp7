package com.plataforma.myp7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RelatorioEstoqueController {

	@RequestMapping("RelatorioEstoque")
	public String produtoAprovacao(Model model){

		return "RelatorioEstoque";
	} 
}
