package com.plataforma.myp7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.PessoaBO;
import com.plataforma.myp7.data.Pessoa;
import com.plataforma.myp7.util.Utils;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaBO pessoaBO;
	
	@RequestMapping("consultarPessoa")
	public String consultarPessoa(Pessoa pessoa, Model model){
		try {
			model.addAttribute("lstPessoa", this.pessoaBO.obterPessoaCodNome(pessoa, model));
		} catch (Exception e) {
			Utils.setMsgRetorno(model, "Falha na operação.");
			Utils.setCodRetorno(model, -1);
		}
		return "PessoaLista";
	}
}
