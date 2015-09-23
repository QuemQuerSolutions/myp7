package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.RepresentanteMapper;
import com.plataforma.myp7.util.Utils;

@Service
public class RepresentanteBO {
	
	@Autowired
	private RepresentanteMapper representanteMapper;
	
	public List<Representante> selecionaTodos(){
		return this.representanteMapper.obterTodosRepresentantes();
	}
	
	public List<Representante> obterPorParametro(Representante representante){
		representante.setApelido(Utils.toLike(representante.getApelido()));
		
		int count = representanteMapper.countPorParametro(representante);
		
		if(count == 0)
			return new ArrayList<Representante>();
		
		if(count > ConfigEnum.LIMITE_COUNT.getValorInt()){
			List<Representante> ret = new ArrayList<Representante>();
			ret.add(new Representante(Mensagem.REFINE_SUA_PESQUISA));
			return ret;
		}
		
		return representanteMapper.obterPorParametro(representante);
	}

	public void update(Representante representante) throws ManterEntidadeException {
		try{
			this.representanteMapper.updateRepresentante(representante);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.ATUALIZA_COMPRADOR_ERRO);
		}
	}

	public void insert(Representante representante) throws ManterEntidadeException {
		try{
			this.representanteMapper.insertRepresentante(representante);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.INSERT_COMPRADOR_ERRO);
		}
	}

	public Representante selecionaPorId(Long idRepresentante) {
		try{
			return this.representanteMapper.obterPorId(idRepresentante);
		}catch(Exception e){
			return null;
		}
	}
	
}
