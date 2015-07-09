package com.plataforma.myp7.mapper;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.NCM;

@Component
public interface NcmMapper {
	
	NCM obterNcmPorCodigo(NCM ncm);
	
	NCM obterNcmPorId(Long idNcm);
}
