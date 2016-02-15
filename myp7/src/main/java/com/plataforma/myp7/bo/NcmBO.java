package com.plataforma.myp7.bo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.NCM;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.NcmMapper;

@Service
public class NcmBO {

	private final static Logger log = Logger.getLogger(NcmBO.class);
			
	@Autowired
	private NcmMapper ncmMapper;
	
	public NCM selecionaPorCodigo(NCM ncm) {
		try {
			return this.ncmMapper.obterNcmPorCodigo(ncm);
		} catch (Exception e) {
			log.error("NcmBO.selecionaPorCodigo", e);
			return null;
		}
	}

	public NCM obterPorCodigo(String codigoNcm) {
		try {
			return this.ncmMapper.obterPorCodigo(codigoNcm);
		} catch (Exception e) {
			log.error("NcmBO.selecionaPorCodigo", e);
			return null;
		}
	}
	
	
	public  void insert(NCM ncm) throws ManterEntidadeException {
		try{
			this.ncmMapper.inserir(ncm);
		}catch(Exception e){
			log.error("NcmBO.insert", e);
			throw new ManterEntidadeException(MensagemWS.INSERT_NCM_ERRO);
		}
		
	}

	public void update(NCM ncm) throws ManterEntidadeException {
		try{
			this.ncmMapper.update(ncm);
		}catch(Exception e){
			log.error("NcmBO.update", e);
			throw new ManterEntidadeException(MensagemWS.ATUALIZA_NCM_ERRO);
		}
	}

	
}

