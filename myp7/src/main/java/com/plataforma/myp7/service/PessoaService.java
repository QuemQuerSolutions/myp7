package com.plataforma.myp7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.PessoaBO;

@Controller
@RequestMapping("/pessoa")
public class PessoaService {

	private Gson gson;
	
	@Autowired
	private PessoaBO pessoaBO;
	
	public PessoaService() {
		this.gson = new Gson();
	}
	
	public String consultaProduto(@RequestParam(value="codigoPessoa", required=false, defaultValue="0") Long codPessoa,
								  @RequestParam(value="nomePessoa", required=false, defaultValue="") String nomePessoa){
		return this.gson.toJson(this.pessoaBO.obterPessoaCodNome(codPessoa, nomePessoa));
	}
	
	public void inserirPessoa(@RequestParam(value="razaoSocial", required=true)String razao){
		
	}
}

