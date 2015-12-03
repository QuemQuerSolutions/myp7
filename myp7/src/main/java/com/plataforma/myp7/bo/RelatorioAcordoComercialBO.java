package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.plataforma.myp7.util.Relatorio;

@Service
public class RelatorioAcordoComercialBO {
	
	public void gerarPDF(HttpServletResponse res){
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		List<String> lista = new ArrayList<String>();
		
		Relatorio.setImageParam(parametros, "logo", "logopeq.jpg");
		Relatorio.gerar(parametros, lista, "rptAcordoComercial", res);
	}
}
