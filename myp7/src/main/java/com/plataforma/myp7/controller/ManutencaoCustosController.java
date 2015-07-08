package com.plataforma.myp7.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.EmpresaBO;
import com.plataforma.myp7.bo.RepresentanteBO;

@Controller
public class ManutencaoCustosController {

	private EmpresaBO empresaBO;
	private RepresentanteBO representanteBO;
	
	public ManutencaoCustosController(){
		this.empresaBO = new EmpresaBO();
		this.representanteBO = new RepresentanteBO();
	}
	
	@RequestMapping("ManutencaoCustos")
	public String inicio(HttpSession session, Model model){
		this.carregaComboEmpresas(model);
		this.carregaComboRepresentantes(model);
		this.carregaComboFiltro(model);
		return "ManutencaoCustos";
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
}
