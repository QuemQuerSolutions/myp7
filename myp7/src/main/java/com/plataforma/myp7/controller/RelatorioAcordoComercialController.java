package com.plataforma.myp7.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.RelatorioAcordoComercialBO;

@Controller
public class RelatorioAcordoComercialController {
	
	@Autowired
	private RelatorioAcordoComercialBO relatorioAcordoComercialBO;
	
	@RequestMapping("rptAcordoComercial")
	public void visualizarAplicacaoFuncional(HttpServletResponse res) {
		relatorioAcordoComercialBO.gerarPDF(res);
	}
}
