package com.plataforma.myp7.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.ProdutoBO;
import com.plataforma.myp7.bo.UsuarioBO;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.Usuario;

@Controller
public class ProdutoAprovacaoController {
	
	@Autowired
	private ProdutoBO produtoBO;
	
	@Autowired
	private UsuarioBO usuarioBO;

	@RequestMapping("ProdutoAprovacao")
	public String produtoAprovacao(Model model, String filtroAnterior){
		model.addAttribute("filtro", filtroAnterior);
		return "ProdutoAprovacaoLista";
	} 
	
	@RequestMapping(value="obterQtdPorSituacao", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Produto> obterQtdPorSituacao(Long idUsuario) {
		return produtoBO.obterQtdPorSituacao(idUsuario);
	}
	
	@RequestMapping(value="obterProdutoAprovacao", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Produto> obterProdutoAprovacao(Produto produto) {
		return produtoBO.obterParaAprovacao(produto);
	}
	
	@RequestMapping(value="aprovarProduto", method=RequestMethod.GET)
	public String aprovarProduto(Long idProduto, HttpSession session){
		Usuario usuario = usuarioBO.getUserSession(session);
		produtoBO.aprovarProduto(idProduto, usuario);
		return "ProdutoAprovacaoLista";
	}
	
	@RequestMapping(value="reprovarProduto", method=RequestMethod.GET)
	public String reprovarProduto(Long idProduto){
		produtoBO.reprovarProduto(idProduto);
		return "ProdutoAprovacaoLista";
	}
}
