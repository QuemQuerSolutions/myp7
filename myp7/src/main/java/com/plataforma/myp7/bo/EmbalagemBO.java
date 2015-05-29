package com.plataforma.myp7.bo;

import static com.plataforma.myp7.Util.Utils.emptyToNull;
import static com.plataforma.myp7.Util.Utils.setMsgRetorno;

import java.util.List;

import org.springframework.ui.Model;

import com.plataforma.myp7.dao.EmbalagemDAO;
import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.dto.ParametrosPesquisaEmbalagens;
import com.plataforma.myp7.enums.GeralEnum;

public class EmbalagemBO {
	
	private EmbalagemDAO embalagemDAO;
	
	public EmbalagemBO(){
		this.embalagemDAO = new EmbalagemDAO();
	}
	
	public List<Embalagem> selecionaPorParametros(ParametrosPesquisaEmbalagens parametros, Model model){
		this.corrigeParametros(parametros);
		
		if(this.count(parametros) > GeralEnum.LIMITE_COUNT.getValor()){
			setMsgRetorno(model, "Refine sua pesquisa.");
			return null;
		}
		
		return this.embalagemDAO.selecionaPorParametros(parametros);
	}
	
	public List<Embalagem> selecionaTodos(){
		return this.embalagemDAO.selecionaTodos();
	}
	
	public Integer count(ParametrosPesquisaEmbalagens parametros){
		return this.embalagemDAO.count(parametros);
	}
	
	private void corrigeParametros(ParametrosPesquisaEmbalagens parametros){
		parametros.setDescricao(emptyToNull(parametros.getDescricao()));
		parametros.setSigla(emptyToNull(parametros.getSigla()));
	}
	
	
	
	
}
