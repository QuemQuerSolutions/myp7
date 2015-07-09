package com.plataforma.myp7.mapper;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.ParametroDominio;

@Component
public interface ParametroDominioMapper {
	
	ParametroDominio obterPorId(Long id);
}
