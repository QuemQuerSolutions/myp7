package com.plataforma.myp7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.plataforma.myp7.bo.PessoaBO;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaBO pessoaBO;
	
	private Gson gson;
	
	public PessoaController() {
		this.gson = new Gson();
	}
	
	@RequestMapping(value="consultarPessoa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String consultarPessoa(Long codPessoa, String nomePessoa){
			return this.gson.toJson(this.pessoaBO.obterPessoaCodNome(codPessoa,nomePessoa));
	}
	
}
