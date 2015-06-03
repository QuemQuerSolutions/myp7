package com.plataforma.myp7.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.ProdutoBO;
import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.data.Produto;

@Controller
public class ProdutoController {
	private ProdutoBO produtoBO;
	
	public ProdutoController() {
		this.produtoBO = new ProdutoBO();
	}
	
	@RequestMapping("Produto")
	public String inicio(HttpSession session, Model model){
		return "ProdutoLista";
	}
	
	@RequestMapping("InserirProduto")
	public String salvar(Produto produto, HttpSession session, Model model){
		produtoBO.salvar(produto);
		
		return "ProdutoLista";
	}
}
