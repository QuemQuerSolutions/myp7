package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.data.NCM;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.EmbalagemMapper;

@Service
public class EmbalagemBO {
	
	private final static Logger log = Logger.getLogger(EmbalagemBO.class);
	
	@Autowired
	private EmbalagemMapper embalagemMapper;
	
	public List<Embalagem> selecionaPorParametros(Embalagem embalagem, Model model){
		try {
			this.corrigeParametros(embalagem);
			Integer count = this.count(embalagem);
			
			if(count == 0){
				cleanLikeEmbalagem(embalagem);
				setMsgRetorno(model, "Nenhum registro localizado.");
				setCodRetorno(model, -1);
				return null;
			}
			
			if(count > Integer.parseInt(ConfigEnum.LIMITE_COUNT.getValor())){
				cleanLikeEmbalagem(embalagem);
				setMsgRetorno(model, "Refine sua pesquisa.");
				setCodRetorno(model, -1);
				return null;
			}
			
			List<Embalagem> lista = this.embalagemMapper.obterEmbalagens(embalagem);
			cleanLikeEmbalagem(embalagem);
			return lista;
		} catch (Exception e) {
			log.error("EmbalagemBO.selecionaPorParametros", e);
			return null;
		}
	}

	private void cleanLikeEmbalagem(Embalagem embalagem) {
		embalagem.setNomeEmbalagem(cleanLike(embalagem.getNomeEmbalagem()));
		embalagem.setSiglaEmbalagem(cleanLike(embalagem.getSiglaEmbalagem()));
	}
	
	public List<Embalagem> selecionaTodos(){
		try {
			return this.embalagemMapper.obterTodasEmbalagens();
		} catch (Exception e) {
			log.error("EmbalagemBO.selecionaTodos", e);
			return null;
		}
	}
	
	public Embalagem selecionaPorId(Long id){
		try {
			return this.embalagemMapper.obterEmbalagemPorId(id);
		} catch (Exception e) {
			log.error("EmbalagemBO.selecionaPorId", e);
			return null;
		}
	}
	
	public Integer count(Embalagem embalagem){
		try {
			return this.embalagemMapper.countEmbalagem(embalagem);
		} catch (Exception e) {
			log.error("EmbalagemBO.count", e);
			return null;
		}
	}
	
	private void corrigeParametros(Embalagem embalagem){
		embalagem.setNomeEmbalagem(emptyToNull(toLike(embalagem.getNomeEmbalagem())));
		embalagem.setSiglaEmbalagem(emptyToNull(toLike(embalagem.getSiglaEmbalagem().toUpperCase())));
	}
	
	public boolean isInsertValido(Embalagem embalagem, Model model){
		try {
			Embalagem embalagemConsulta = new Embalagem();
			embalagemConsulta.setSiglaEmbalagem(embalagem.getSiglaEmbalagem());
			
			if(this.embalagemMapper.obterEmbalagens(embalagemConsulta).size() > 0){
				setMsgRetorno(model, "Embalagem existente");
				setCodRetorno(model, -1);
				model.addAttribute("outraPagina", "insert");
				model.addAttribute("embalagem", embalagem);
				return false;
			}
			return true;
		} catch (Exception e) {
			log.error("EmbalagemBO.isInsertValido", e);
			return true;
		}
	}
	
	public boolean salvar(Embalagem embalagem, Model model) {
		try {
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
		} catch (Exception e) {
			setMsgRetorno(model, "Erro ao inserir a embalagem");
			setCodRetorno(model, -1);
			model.addAttribute("outraPagina", "insert");
			model.addAttribute("embalagem", embalagem);
			log.error("EmbalagemBO.salvar", e);
			return true;
		}
	}
	
	public Embalagem obterEmbalagemPorId(Long id){
		try {
			return this.embalagemMapper.obterEmbalagemPorId(id);
		} catch (Exception e) {
			log.error("EmbalagemBO.obterEmbalagemPorId", e);
			return null;
		}
	}

	public Embalagem obterEmbalagemPorSigla(String siglaEmbalagem){
		try {
			return this.embalagemMapper.obterEmbalagemPorSigla(siglaEmbalagem);
		} catch (Exception e) {
			log.error("EmbalagemBO.obterEmbalagemPorId", e);
			return null;
		}
	}
	
	
	public  void insert(Embalagem embalagem) throws ManterEntidadeException {
		try{
			this.embalagemMapper.salvarEmbalagem(embalagem);
		}catch(Exception e){
			log.error("EmbalagemBO.insert", e);
			throw new ManterEntidadeException(MensagemWS.INSERT_EMBALAGEM_ERRO);
		}
		
	}

	public void update(Embalagem embalagem) throws ManterEntidadeException {
		try{
			this.embalagemMapper.atualizarEmbalagem(embalagem);
		}catch(Exception e){
			log.error("EmbalagemBO.update", e);
			throw new ManterEntidadeException(MensagemWS.ATUALIZA_EMBALAGEM_ERRO);
		}
	}


}

