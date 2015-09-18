package com.plataforma.myp7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.PessoaBO;
import com.plataforma.myp7.data.Pessoa;
import com.plataforma.myp7.util.Utils;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaBO pessoaBO;
	
	@RequestMapping("consultarPessoa")
	public @ResponseBody String consultarPessoa(String codPessoa, String nomePessoa, Model model){
		try {
			Pessoa pessoa = new Pessoa();
			pessoa.setIdPessoa(codPessoa.equals("")?null:Long.parseLong(codPessoa));
			pessoa.setRazao(nomePessoa.equals("")?null:nomePessoa);
			return this.gerarTabelaResultadoPessoa(this.pessoaBO.obterPessoaCodNome(pessoa, model));
		} catch (Exception e) {
			Utils.setMsgRetorno(model, "Falha na operação.");
			Utils.setCodRetorno(model, -1);
		}
		return null;
	}
	
	
	private String gerarTabelaResultadoPessoa(List<Pessoa> lstPessoa){
		StringBuilder concat = new StringBuilder();
		
		for(Pessoa p:lstPessoa){
			concat.append("<tr>");
			concat.append("		<td>");
			concat.append("			" + p.getIdPessoa());
			concat.append("		</td>");
			concat.append("		<td>");
			concat.append("			" + p.getRazao());
			concat.append("		</td>");
			concat.append("</tr>");
		}
		
		return concat.toString();
	}
}
