package com.plataforma.myp7.mapper;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.NCM;

@Component
public interface NcmMapper {
	
	NCM obterNcmPorCodigo(NCM ncm);

	NCM obterPorCodigo(String codigoNcm);
	
	NCM obterNcmPorId(Long idNcm);

	void inserir(NCM ncm);

	void update(NCM ncm);
	
}

