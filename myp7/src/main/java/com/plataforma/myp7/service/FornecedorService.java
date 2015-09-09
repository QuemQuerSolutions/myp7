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
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.exception.ManterEntidadeException;

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
	public String cadastrarForncedor(@RequestParam(value="idFornecedor", required=false) Long idFornecedor,
			   					   @RequestParam(value="status", required=true) String status,
			   					   @RequestParam(value="utilizaTabCusto", required=true) String utilizaTabCusto){
		Mensagem mensagem;
		
	    Fornecedor fornecedor = new Fornecedor();
	    fornecedor.setStatusFornecedor(status);
	    fornecedor.setUtilTabCustoFornc(utilizaTabCusto);
		try {
			if(idFornecedor != null){
				fornecedor.setIdFornecedor(idFornecedor);
				this.fornecedorBO.update(fornecedor);
				mensagem = Mensagem.ATUALIZA_FORNC_SUCESSO;
			}else{
				this.fornecedorBO.inserir(fornecedor);
				mensagem = Mensagem.INSERT_FORNC_SUCESSO;
			}
			return gson.toJson(Mensagem.getMensagem(mensagem));
		} catch (ManterEntidadeException e) {
			return gson.toJson(Mensagem.getMensagem(e.getMensagemEnum()));
		}
	}
}
