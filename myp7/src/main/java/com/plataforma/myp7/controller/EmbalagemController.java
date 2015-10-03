package com.plataforma.myp7.controller;

import static com.plataforma.myp7.util.Utils.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.EmbalagemBO;
import com.plataforma.myp7.data.Embalagem;

@Controller
public class EmbalagemController {
	
	@Autowired
	private EmbalagemBO embalagemBO;
	
	@RequestMapping("Embalagem")
	public String inicio(HttpSession session, Model model){
		List<Embalagem> embalagens = this.carregaLista();
		
		model.addAttribute("lista", embalagens);
		
		return "EmbalagemLista";
	}
	
	@RequestMapping("CarregaListaEmbalagem")
	public String consulta(Embalagem parametros, HttpSession session, Model model){
		List<Embalagem> embalagens = this.carregaLista(parametros, model);
		
		model.addAttribute("lista", embalagens);
		model.addAttribute("emb", parametros);
		
		return "EmbalagemLista";
	}
	
	@RequestMapping("SalvarEmbalagem")
	public String salvar(Embalagem embalagem, Model model){
		try{
			this.embalagemBO.salvar(embalagem, model);
			model.addAttribute("lista", this.carregaLista());
			return "EmbalagemLista";
		}catch(Exception e){
			e.printStackTrace();
			setMsgRetorno(model, "Erro ao inserir a embalagem");
			setCodRetorno(model, -1);
			model.addAttribute("outraPagina", "insert");
			model.addAttribute("embalagem", embalagem);
			return "EmbalagemLista";
		}
	}
	
	private List<Embalagem> carregaLista(Embalagem parametros, Model model){
		return this.embalagemBO.selecionaPorParametros(parametros, model);
	}
	
	private List<Embalagem> carregaLista(){
		return this.embalagemBO.selecionaTodos();
	}
	
	
}
