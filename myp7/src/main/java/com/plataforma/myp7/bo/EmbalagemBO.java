package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.*;

import java.util.List;

import org.springframework.ui.Model;

import com.plataforma.myp7.dao.EmbalagemDAO;
import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.enums.GeralEnum;

public class EmbalagemBO {
	
	private EmbalagemDAO embalagemDAO;
	
	public EmbalagemBO(){
		this.embalagemDAO = new EmbalagemDAO();
	}
	
	public List<Embalagem> selecionaPorParametros(Embalagem embalagem, Model model){
		this.corrigeParametros(embalagem);
		
		if(this.count(embalagem) > GeralEnum.LIMITE_COUNT.getValor()){
			setMsgRetorno(model, "Refine sua pesquisa.");
			return null;
		}
		
		return this.embalagemDAO.selecionaPorParametros(embalagem);
	}
	
	public List<Embalagem> selecionaTodos(){
		return this.embalagemDAO.selecionaTodos();
	}
	
	public Integer count(Embalagem embalagem){
		return this.embalagemDAO.count(embalagem);
	}
	
	private void corrigeParametros(Embalagem embalagem){
		embalagem.setNomeEmbalagem(emptyToNull(embalagem.getNomeEmbalagem()));
		embalagem.setSiglaEmbalagem(emptyToNull(embalagem.getSiglaEmbalagem()));
	}
	
	public boolean isInsertValido(Embalagem embalagem, Model model){
		Embalagem embalagemConsulta = new Embalagem();
		embalagemConsulta.setSiglaEmbalagem(embalagem.getSiglaEmbalagem());
		
		if(this.embalagemDAO.selecionaPorParametros(embalagemConsulta).size() > 0){
			setMsgRetorno(model, "Embalagem já existente");
			setCodRetorno(model, -1);
			model.addAttribute("outraPagina", "insert");
			model.addAttribute("embalagem", embalagem);
			return false;
		}
		
		return true;
	}
	
	public void salvar(Embalagem embalagem, Model model) throws Exception {
		if(this.isInsertValido(embalagem, model)){
			this.embalagemDAO.salvar(embalagem);
			setMsgRetorno(model, "Embalagem salva com sucesso");
			setCodRetorno(model, 0);
		}
	}
}
