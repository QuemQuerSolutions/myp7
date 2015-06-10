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
		Integer count = this.count(embalagem);
		
		if(count == 0){
			setMsgRetorno(model, "Nenhum registro localizado.");
			setCodRetorno(model, -1);
			return null;
		}
		
		if(count > Integer.parseInt(GeralEnum.LIMITE_COUNT.getValor())){
			setMsgRetorno(model, "Refine sua pesquisa.");
			setCodRetorno(model, -1);
			return null;
		}
		
		return this.embalagemDAO.selecionaPorParametros(embalagem);
	}
	
	public List<Embalagem> selecionaTodos(){
		return this.embalagemDAO.selecionaTodos();
	}
	
	public Embalagem selecionaPorId(Long id){
		return this.embalagemDAO.selecionaPorId(id);
	}
	
	public Integer count(Embalagem embalagem){
		return this.embalagemDAO.count(embalagem);
	}
	
	private void corrigeParametros(Embalagem embalagem){
		embalagem.setNomeEmbalagem(emptyToNull(embalagem.getNomeEmbalagem()));
		embalagem.setSiglaEmbalagem(emptyToNull(embalagem.getSiglaEmbalagem().toUpperCase()));
	}
	
	public boolean isInsertValido(Embalagem embalagem, Model model){
		
		Embalagem embalagemConsulta = new Embalagem();
		embalagemConsulta.setSiglaEmbalagem(embalagem.getSiglaEmbalagem());
		embalagemConsulta.setQtdEmbalagem(embalagem.getQtdEmbalagem());
		
		if(this.embalagemDAO.selecionaPorParametros(embalagemConsulta).size() > 0){
			setMsgRetorno(model, "Embalagem já existente");
			setCodRetorno(model, -1);
			model.addAttribute("outraPagina", "insert");
			model.addAttribute("embalagem", embalagem);
			return false;
		}
		return true;
	}
	
	public boolean salvar(Embalagem embalagem, Model model) throws Exception {
			embalagem.setSiglaEmbalagem(embalagem.getSiglaEmbalagem().toUpperCase());
			
			if(embalagem.getIdEmbalagem() != 0)
				this.embalagemDAO.update(embalagem);
			else{
				if(!this.isInsertValido(embalagem, model))
					return false;
				this.embalagemDAO.insert(embalagem);
			}
			setMsgRetorno(model, "Embalagem salva com sucesso");
			setCodRetorno(model, 0);
			return true;
	}
	
	public Embalagem obterEmbalagemPorId(Long id){
		return this.embalagemDAO.selecionaPorId(id);
	}
	
}
