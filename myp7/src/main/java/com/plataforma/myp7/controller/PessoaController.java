package com.plataforma.myp7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.PessoaBO;
import com.plataforma.myp7.data.Pessoa;

@Controller
@RequestMapping(value={"/retaguarda", "/admin"})
public class PessoaController {
	
	@Autowired
	private PessoaBO pessoaBO;
	
	@RequestMapping(value="consultarPessoa", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Pessoa> consultarPessoa(Pessoa pessoa){
			return this.pessoaBO.obterPessoaPorParametro(pessoa);
	}
	
}
