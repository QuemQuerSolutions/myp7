package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.RelatorioAcordoComercial;
import com.plataforma.myp7.util.Relatorio;

@Service
public class RelatorioAcordoComercialBO {
	
	public void gerarPDF(HttpServletResponse res){
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		List<RelatorioAcordoComercial> lista = new ArrayList<RelatorioAcordoComercial>();
		
		mockTemp(lista);
		
		Relatorio.setImageParam(parametros, "logo", "logopeq.jpg");
		Relatorio.gerar(parametros, lista, "rptAcordoComercial", res);
	}
	
	public List<RelatorioAcordoComercial> obterPorParametro(RelatorioAcordoComercial rac){
		
		List<RelatorioAcordoComercial> lista = new ArrayList<RelatorioAcordoComercial>();
		
		mockTemp(lista);
		return lista;
	}
	
	private void mockTemp(List<RelatorioAcordoComercial> lista){
		RelatorioAcordoComercial rac = new RelatorioAcordoComercial();
		rac.setNroTitulo(145643);
		rac.setEspecie("Devolução");
		rac.setComprador("Comprador teste 01 mock");
		rac.setRepresentante("Representante de teste mock 01");
		rac.setFornecedor("Esse fornecedor é de teste para o relatório");
		rac.setDataInclusao(new Date());
		rac.setDataVencimento(new Date());
		rac.setValor(2343243.1);
		rac.setSituacao("Vigente");
		
		lista.add(rac);
		
		rac = new RelatorioAcordoComercial();
		rac.setNroTitulo(145643);
		rac.setEspecie("Devolução");
		rac.setComprador("Comprador teste 01 mock");
		rac.setRepresentante("Representante de teste mock 01");
		rac.setFornecedor("Esse fornecedor é de teste para o relatório");
		rac.setDataInclusao(new Date());
		rac.setDataVencimento(new Date());
		rac.setValor(2343243.99);
		rac.setSituacao("Vigente");
		
		lista.add(rac);
	}
}
