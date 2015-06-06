package com.plataforma.myp7.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.EmbalagemBO;
import com.plataforma.myp7.bo.ProdutoBO;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.util.Utils;

@Controller
public class ProdutoController {
	private ProdutoBO produtoBO;
	private EmbalagemBO embalagemBO;
	
	public ProdutoController() {
		this.produtoBO = new ProdutoBO();
		this.embalagemBO = new EmbalagemBO();
	}
	
	@RequestMapping("Produto")
	public String inicio(HttpSession session, Model model){
		return "ProdutoLista";
	}
	
	@RequestMapping("NovoProduto")
	public String novo(HttpSession session, Model model){
		this.carregaSelectEmbalagem(model);
		return "ProdutoInserir";
	}
	
	@RequestMapping("InserirProduto")
	public String salvar(Produto produto, HttpSession session, Model model){
		if(!produtoBO.salvar(produto, model)){
			model.addAttribute("produto", produto);
		}
		
		this.carregaSelectEmbalagem(model);
		return "ProdutoInserir";
	}
	
	private void carregaSelectEmbalagem(Model model){
		model.addAttribute("embalagens", this.embalagemBO.selecionaTodos());
	}
	
	@RequestMapping("carregaProdutos")
	public String obterEmbalagens(Produto produto, HttpSession session, Model model){
		try {
			model.addAttribute("produtos", this.produtoBO.obterProdutos(produto, model));
		} catch (SQLException e) {
			Utils.setMsgRetorno(model, "Falha na operação.");
			Utils.setCodRetorno(model, -1);
		}
		return "ProdutoLista";
	}
}
