package com.plataforma.myp7.controller;

import static com.plataforma.myp7.Util.Utils.setMsgRetorno;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.EmbalagemBO;
import com.plataforma.myp7.data.Embalagem;

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
	public String consulta(Embalagem parametros, HttpSession session, Model model){
		List<Embalagem> embalagens = this.carregaLista(parametros, model);
		
		model.addAttribute("lista", embalagens);
		
		return "EmbalagemLista";
	}
	
	@RequestMapping("InserirEmbalagem")
	public String salvar(Embalagem embalagem, Model model){
		String validacao = this.embalagemBO.validaInsert(embalagem, model);
		if(validacao == null){
			if(this.embalagemBO.salvar(embalagem)){
				return "redirect:Embalagem";
			}else{
				setMsgRetorno(model, "Erro ao inserir a embalagem.");
				model.addAttribute("outraPagina", "insert");
				model.addAttribute("embalagem", embalagem);
				return "EmbalagemLista";
			}
		}else{
			model.addAttribute("outraPagina", "insert");
			model.addAttribute("embalagem", embalagem);
			return validacao;
		}
	}
	
	private List<Embalagem> carregaLista(Embalagem parametros, Model model){
		return this.embalagemBO.selecionaPorParametros(parametros, model);
	}
	
	private List<Embalagem> carregaLista(){
		return this.embalagemBO.selecionaTodos();
	}
	
	
}
