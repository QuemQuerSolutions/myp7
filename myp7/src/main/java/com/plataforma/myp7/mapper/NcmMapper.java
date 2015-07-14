package com.plataforma.myp7.mapper;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.NCM;

@Component
public interface NcmMapper {
	
	NCM obterNcmPorCodigo(NCM codNcm);
	
	NCM obterNcmPorId(Long idNcm);
}
