package com.plataforma.myp7.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.RelatorioAcordoComercial;
import com.plataforma.myp7.enums.SituacaoTituloEnum;
import com.plataforma.myp7.mapper.RelatorioAcordoComercialMapper;
import com.plataforma.myp7.util.DateUtils;
import com.plataforma.myp7.util.Relatorio;

@Service
public class RelatorioAcordoComercialBO {
	
	private final static Logger log = Logger.getLogger(RelatorioAcordoComercialBO.class);
	
	@Autowired
	private RelatorioAcordoComercialMapper relatorioAcordoComercialMapper;
	
	public void gerarPDF(HttpServletResponse res, RelatorioAcordoComercial rac){
		Map<String, Object> parametros = new HashMap<String, Object>();
		setFilters(rac);

		List<RelatorioAcordoComercial> lista = getLista(rac);
		
		Relatorio.setImageParam(parametros, "logo", "logopeq.jpg");
		Relatorio.gerar(parametros, lista, "rptAcordoComercial", res);
	}

	private List<RelatorioAcordoComercial> getLista(RelatorioAcordoComercial rac) {
		try {
			List<RelatorioAcordoComercial> lista = relatorioAcordoComercialMapper.obterPorParametro(rac);
			
			for(RelatorioAcordoComercial i: lista){
				i.setSituacao(SituacaoTituloEnum.getNome(i.getSituacao()));
			}
			return lista;
		} catch (Exception e) {
			log.error("RelatorioAcordoComercialBO.getLista", e);
			return null;
		}
	}
	
	public List<RelatorioAcordoComercial> obterPorParametro(RelatorioAcordoComercial rac){
		setFilters(rac);
		
		List<RelatorioAcordoComercial> lista = getLista(rac);
		
		return lista;
	}
	
	private void setFilters(RelatorioAcordoComercial rac){
		try {
			String formatoDataTela = "yyyy-MM-dd";
			rac.setDataInclusaoDeDate(DateUtils.getDate(rac.getDataInclusaoDe(), formatoDataTela));
			rac.setDataInclusaoAteDate(DateUtils.getDate(rac.getDataInclusaoAte(), formatoDataTela));
			rac.setDataVencimentoDeDate(DateUtils.getDate(rac.getDataVencimentoDe(), formatoDataTela));
			rac.setDataVencimentoAteDate(DateUtils.getDate(rac.getDataVencimentoAte(), formatoDataTela));
		} catch (Exception e) {
			log.error("RelatorioAcordoComercialBO.setFilters", e);
		}
	}
	
}
