package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Parametro;
import com.plataforma.myp7.data.ParametroDominio;
import com.plataforma.myp7.data.ParametroTipo;

@Component
public interface ParametroMapper {
	
	List<Parametro> obterParametro(int id) throws Exception;
	
	Parametro obterParametroPorNome(String nome);
	
	ParametroTipo obterPorTipo(Long tipo);
	
	List<ParametroDominio> obterPorDominio(ParametroDominio dominio);
	
}
