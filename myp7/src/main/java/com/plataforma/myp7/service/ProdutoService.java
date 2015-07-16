package com.plataforma.myp7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.ProdutoBO;
import com.plataforma.myp7.data.Produto;

@Controller
@RequestMapping("/produto")
public class ProdutoService {
	private Gson gson;
	
	public ProdutoService() {
		this.gson = new Gson();
	}
	
	@Autowired
	private ProdutoBO produtoBO;
	
	@RequestMapping(method=RequestMethod.GET, value="/consultaProduto",produces="application/json")
	public String consultaProduto(@RequestParam(value="id", required=false, defaultValue="0")Long id
								 ,@RequestParam(value="descricao", required=false, defaultValue="") String desc){
		try {
			Produto produto = new Produto();
			produto.setIdProduto(id);
			produto.setDesProduto(desc);
			
			return this.gson.toJson(produtoBO.consultaProdutoService(produto));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
