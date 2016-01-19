package com.plataforma.myp7.bo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.NCM;
import com.plataforma.myp7.mapper.NcmMapper;

@Service
public class NcmBO {

	private final static Logger log = Logger.getLogger(NcmBO.class);
			
	@Autowired
	private NcmMapper ncmMapper;
	
	public NCM selecionaPorCodigo(NCM ncmProduto){
		try {
			return ncmMapper.obterNcmPorCodigo(ncmProduto);
		} catch (Exception e) {
			log.error("NcmBO.selecionaPorCodigo", e);
			return null;
		}
	}
}
