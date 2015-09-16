package com.plataforma.myp7.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.RepresentanteMapper;

@Service
public class RepresentanteBO {
	
	@Autowired
	private RepresentanteMapper representanteMapper;
	
	public List<Representante> selecionaTodos(){
		return this.representanteMapper.obterTodosRepresentantes();
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
