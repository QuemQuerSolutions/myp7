package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.RelatorioEstoque;
import com.plataforma.myp7.mapper.RelatorioEstoqueMapper;
import com.plataforma.myp7.util.Relatorio;

@Service
public class RelatorioEstoqueBO {

	@Autowired
	private RelatorioEstoqueMapper relatorioEstoqueMapper;
	
	
	public void gerarPDF(HttpServletResponse res, RelatorioEstoque relEstoque){
		try{
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			List<RelatorioEstoque> lstEstoques = setFormatDate(relatorioEstoqueMapper.obterPorParametro(relEstoque));
			
			Relatorio.setImageParam(parametros, "logo", "logopeq.jpg");
			Relatorio.gerar(parametros, lstEstoques, "rpdEstoque", res);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<RelatorioEstoque> obterPorParametros(RelatorioEstoque relatorioEstoque){
		try{
			return setFormatDate(relatorioEstoqueMapper.obterPorParametro(relatorioEstoque));
		}catch(Exception e){
			return null;
		}
	}
	
	public List<RelatorioEstoque> setFormatDate(List<RelatorioEstoque> lstRel){
		List<RelatorioEstoque> lstRelTransformado = new ArrayList<RelatorioEstoque>();
		for(RelatorioEstoque iRel: lstRel){
			iRel.setDataUltimaCompra(iRel.getDataUltimaCompra().substring(0, iRel.getDataUltimaCompra().indexOf(":")-3));
			lstRelTransformado.add(iRel);
		}
		return lstRel;
	}
}
