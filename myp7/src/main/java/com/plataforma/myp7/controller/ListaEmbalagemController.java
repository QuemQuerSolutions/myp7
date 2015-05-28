package com.plataforma.myp7.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.dao.EmbalagemDAO;
import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.dto.ParametrosPesquisaEmbalagens;

@Controller
public class ListaEmbalagemController {

	@RequestMapping("Embalagem")
	public String inicio(HttpSession session, Model model){
		List<Embalagem> embalagens = carregaLista(null);
		
		model.addAttribute("lista", embalagens);
		
		return "EmbalagemLista";
	}
	
	@RequestMapping("CarregaListaEmbalagem")
	public String consulta(ParametrosPesquisaEmbalagens parametros, HttpSession session, Model model){
		List<Embalagem> embalagens = carregaLista(parametros);
		
		model.addAttribute("lista", embalagens);
		
		return "EmbalagemLista";
	}
	
	private List<Embalagem> carregaLista(ParametrosPesquisaEmbalagens parametros){
		EmbalagemDAO embalagemDAO = new EmbalagemDAO();
		
		if(parametros != null){
			return embalagemDAO.selecionaPorParametros(parametros);
		}else{
			return embalagemDAO.selecionaTodos();
		}
	}
}
