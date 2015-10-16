package com.plataforma.myp7.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.FornecedorCustoBO;
import com.plataforma.myp7.bo.PessoaBO;
import com.plataforma.myp7.data.FornecedorCusto;
import com.plataforma.myp7.enums.SituacaoEnum;

@Controller
public class CustoAprovacaoController {
	
	@Autowired
	private FornecedorCustoBO fornecedorCustoBO;
	
	@Autowired
	private PessoaBO pessoaBO;

	@RequestMapping("CustoAprovacao")
	public String custoAprovacao(Model model){
		this.carregaSelectUf(model);
		this.carregaSelectTipo(model);
		
		return "CustoAprovacaoLista";
	} 
	
	@RequestMapping(value="obterQtdFornecedorCustoPorSituacao", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FornecedorCusto> obterQtdFornecedorCustoPorSituacao(Long idUsuario) {
		return fornecedorCustoBO.obterQtdPorSituacao(idUsuario);
	}
	
	@RequestMapping(value="obterFornecedorCustoAprovacao", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FornecedorCusto> obterFornecedorCustoAprovacao(FornecedorCusto fornecedorCusto, String tipo, String codigo) {
		return fornecedorCustoBO.obterParaAprovacao(fornecedorCusto, codigo, tipo);
	}
	
	@RequestMapping(value="aprovarFornecedorCusto", method=RequestMethod.GET)
	public String aprovarFornecedorCusto(Long[] idFornecedorCusto){
		fornecedorCustoBO.alterarSituacaoFornecedorCusto(idFornecedorCusto, SituacaoEnum.APROVADO);
		return "CustoAprovacaoLista";
	}
	
	@RequestMapping(value="reprovarFornecedorCusto", method=RequestMethod.GET)
	public String reprovarFornecedorCusto(Long[] idFornecedorCusto){
		fornecedorCustoBO.alterarSituacaoFornecedorCusto(idFornecedorCusto, SituacaoEnum.REPROVADO);
		return "CustoAprovacaoLista";
	}
	
	private void carregaSelectUf(Model model){
		model.addAttribute("ufs", this.pessoaBO.selecionaTodasUF());
	}
	
	private void carregaSelectTipo(Model model){
		Map<Integer, String> filtro = new HashMap<Integer, String>();
		filtro.put(1, "Código");
		filtro.put(2, "EAN/DUN");
		
		model.addAttribute("filtros", filtro);
	}	
}
