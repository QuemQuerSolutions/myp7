package com.plataforma.myp7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.FornecedorBO;
import com.plataforma.myp7.util.Utils;

@Controller
public class FornecedorController {
	
	@Autowired
	private FornecedorBO fornecedorBO;
	
	@RequestMapping("Fornecedor")
	public String inicio(){
		return "FornecedorLista";
	}
	
	@RequestMapping("carregaListaFornecedor")
	public String carregaListaForncedor(Long idFornecedor, String cnpjFornecedor, Model model){
		try{
			model.addAttribute("lstFornecedor", this.fornecedorBO.obterFornecedorPorParametro(idFornecedor, cnpjFornecedor, model));
		}catch(Exception e){
			Utils.setMsgRetorno(model, "Falha na Operação");
			Utils.setCodRetorno(model, -1);
		}
		return "FornecedorLista";
	}
}
