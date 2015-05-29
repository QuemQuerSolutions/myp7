package com.plataforma.myp7.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.EmbalagemBO;
import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.dto.ParametrosPesquisaEmbalagens;

@Controller
public class EmbalagemController {
	
	private EmbalagemBO embalagemBO;
	
	public EmbalagemController() {
		this.embalagemBO = new EmbalagemBO();
	}

	@RequestMapping("Embalagem")
	public String inicio(HttpSession session, Model model){
		List<Embalagem> embalagens = this.carregaLista();
		
		model.addAttribute("lista", embalagens);
		
		return "EmbalagemLista";
	}
	
	@RequestMapping("CarregaListaEmbalagem")
	public String consulta(ParametrosPesquisaEmbalagens parametros, HttpSession session, Model model){
		List<Embalagem> embalagens = this.carregaLista(parametros, model);
		
		model.addAttribute("lista", embalagens);
		
		return "EmbalagemLista";
	}
	
	private List<Embalagem> carregaLista(ParametrosPesquisaEmbalagens parametros, Model model){
		return this.embalagemBO.selecionaPorParametros(parametros, model);
	}
	
	private List<Embalagem> carregaLista(){
		return this.embalagemBO.selecionaTodos();
	}
	
	
}
