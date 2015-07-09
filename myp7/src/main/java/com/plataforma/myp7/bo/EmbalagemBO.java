package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.emptyToNull;
import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.mapper.EmbalagemMapper;

@Service
public class EmbalagemBO {
	
	@Autowired
	private EmbalagemMapper embalagemMapper;
	
	public List<Embalagem> selecionaPorParametros(Embalagem embalagem, Model model){
		this.corrigeParametros(embalagem);
		Integer count = this.count(embalagem);
		
		if(count == 0){
			setMsgRetorno(model, "Nenhum registro localizado.");
			setCodRetorno(model, -1);
			return null;
		}
		
		if(count > Integer.parseInt(ConfigEnum.LIMITE_COUNT.getValor())){
			setMsgRetorno(model, "Refine sua pesquisa.");
			setCodRetorno(model, -1);
			return null;
		}
		
		return this.embalagemMapper.obterEmbalagens(embalagem);
	}
	
	public List<Embalagem> selecionaTodos(){
		return this.embalagemMapper.obterTodasEmbalagens();
	}
	
	public Embalagem selecionaPorId(Long id){
		return this.embalagemMapper.obterEmbalagemPorId(id);
	}
	
	public Integer count(Embalagem embalagem){
		return this.embalagemMapper.countEmbalagem(embalagem);
	}
	
	private void corrigeParametros(Embalagem embalagem){
		embalagem.setNomeEmbalagem(emptyToNull(embalagem.getNomeEmbalagem()));
		embalagem.setSiglaEmbalagem(emptyToNull(embalagem.getSiglaEmbalagem().toUpperCase()));
	}
	
	public boolean isInsertValido(Embalagem embalagem, Model model){
		
		Embalagem embalagemConsulta = new Embalagem();
		embalagemConsulta.setSiglaEmbalagem(embalagem.getSiglaEmbalagem());
		embalagemConsulta.setQtdEmbalagem(embalagem.getQtdEmbalagem());
		
		if(this.embalagemMapper.obterEmbalagens(embalagemConsulta).size() > 0){
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
				this.embalagemMapper.atualizarEmbalagem(embalagem);
			else{
				if(!this.isInsertValido(embalagem, model))
					return false;
				this.embalagemMapper.salvarEmbalagem(embalagem);
			}
			setMsgRetorno(model, "Embalagem salva com sucesso");
			setCodRetorno(model, 0);
			return true;
	}
	
	public Embalagem obterEmbalagemPorId(Long id){
		return this.embalagemMapper.obterEmbalagemPorId(id);
	}
	
}
