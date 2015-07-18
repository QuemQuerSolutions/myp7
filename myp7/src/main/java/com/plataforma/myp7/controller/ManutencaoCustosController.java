package com.plataforma.myp7.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.EmpresaBO;
import com.plataforma.myp7.bo.FornecedorCustoBO;
import com.plataforma.myp7.bo.PessoaBO;
import com.plataforma.myp7.bo.RepresentanteBO;

@Controller
public class ManutencaoCustosController {

	@Autowired
	private EmpresaBO empresaBO;
	
	@Autowired
	private RepresentanteBO representanteBO;
	
	@Autowired
	private FornecedorCustoBO fornecedorCustoBO;
	
	@Autowired
	private PessoaBO pessoaBO;	
	
	@RequestMapping("ManutencaoCustos")
	public String inicio(HttpSession session, Model model){
		this.carregaComboEmpresas(model);
		this.carregaComboRepresentantes(model);
		this.carregaComboFiltro(model);
		this.consulta(model);
		this.carregaComboUF(model);
		
		return "ManutencaoCustos";
	}
	
	private void carregaComboUF(Model model) {
		model.addAttribute("ufs", this.pessoaBO.selecionaTodasUF());
	}

	private void carregaComboEmpresas(Model model){
		model.addAttribute("empresas", this.empresaBO.selecionaTodos());
	}
	
	private void carregaComboRepresentantes(Model model){
		model.addAttribute("representantes", this.representanteBO.selecionaTodos());
	}
	
	private void carregaComboFiltro(Model model){
		Map<Integer, String> filtro = new HashMap<Integer, String>();
		filtro.put(1, "Código");
		filtro.put(2, "EAN/DUN");
		
		model.addAttribute("filtros", filtro);
	}
	
	@RequestMapping("consulta")
	public @ResponseBody void consultaAJAX(Model model) {
		this.consulta(model);
	}

	private void consulta(Model model) {
		model.addAttribute("fornecedorCusto", this.fornecedorCustoBO.seleciona());
	}
}
