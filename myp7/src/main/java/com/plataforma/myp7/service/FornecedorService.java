package com.plataforma.myp7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.FornecedorBO;
import com.plataforma.myp7.data.Fornecedor;

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
	

	@RequestMapping(method=RequestMethod.POST, value="/inserirFornecedor", produces="application/json")
	public void inserirForncedor(@RequestParam(value="status", required=true) String status,
								 @RequestParam(value="utilizaTabCusto", required=true)String utilizaTabCusto){
	    Fornecedor fornecedor = new Fornecedor();
	    fornecedor.setStatusFornecedor(status);
	    fornecedor.setUtilTabCustoFornc(utilizaTabCusto);
		this.fornecedorBO.inserir(fornecedor);
	}
}
