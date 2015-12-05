package com.plataforma.myp7.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.RelatorioAcordoComercialBO;
import com.plataforma.myp7.data.RelatorioAcordoComercial;

@Controller
public class RelatorioAcordoComercialController {
	
	@Autowired
	private RelatorioAcordoComercialBO relatorioAcordoComercialBO;
	
	@RequestMapping("rptAcordoComercial")
	public void visualizarAplicacaoFuncional(HttpServletResponse res) {
		relatorioAcordoComercialBO.gerarPDF(res);
	}
	
	@RequestMapping("RelatorioAcordoComercial")
	public String relatorioAcordoComercial() {
		return "RelatorioAcordoComercial";
	}
	
	@RequestMapping(value="pesquisaRelatorioTitulo")
	public String pesquisaRelatorioTitulo(Model model, RelatorioAcordoComercial relatorioAcordoComercial) {
		
		model.addAttribute("obj", relatorioAcordoComercial);
		model.addAttribute("lstRelatorio", relatorioAcordoComercialBO.obterPorParametro(relatorioAcordoComercial));
		
		return "RelatorioAcordoComercial";
	}
}
