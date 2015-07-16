package com.plataforma.myp7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.FornecedorBO;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorService {
	
	@Autowired
	private FornecedorBO fornecedorBO;
	
	private Gson gson;
	
	public FornecedorService(){
		this.gson = new Gson();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/consultaFornecedor",produces="application/json")
	public String consultaFornecedor(){
		return this.gson.toJson(this.fornecedorBO.obterTodos());
	}
	
}
