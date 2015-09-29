package com.plataforma.myp7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.ProdutoBO;

@Controller
public class ProdutoAprovacaoController {
	
	@Autowired
	private ProdutoBO produtoBO;

	@RequestMapping("ProdutoAprovacao")
	public String produtoAprovacao(){
		return "ProdutoAprovacaoLista";
	}
}
