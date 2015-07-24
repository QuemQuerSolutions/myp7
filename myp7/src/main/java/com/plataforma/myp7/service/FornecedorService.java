package com.plataforma.myp7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.FornecedorBO;
import com.plataforma.myp7.data.Fornecedor;
import com.plataforma.myp7.util.Utils;

@Controller
@RequestMapping("/wsfornecedor")
public class FornecedorService {
	
	@Autowired
	private FornecedorBO fornecedorBO;
	
	private Gson gson;
	
	public FornecedorService(){
		this.gson = new Gson();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/inserirFornecedor", produces="application/json")
	@ResponseBody
	public String inserirForncedor(@RequestParam(value="status", required=true) String status,
								 @RequestParam(value="utilizaTabCusto", required=true)String utilizaTabCusto){
	    Fornecedor fornecedor = new Fornecedor();
	    fornecedor.setStatusFornecedor(status);
	    fornecedor.setUtilTabCustoFornc(utilizaTabCusto);
		try {
			this.fornecedorBO.inserir(fornecedor);
			return gson.toJson(Utils.formataMsgem(5));
		} catch (Exception e) {
			return gson.toJson(Utils.formataMsgem(4));
		}
	}
}
