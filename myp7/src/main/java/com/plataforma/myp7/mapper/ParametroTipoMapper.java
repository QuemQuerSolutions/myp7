package com.plataforma.myp7.mapper;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.ParametroTipo;

@Component
public interface ParametroTipoMapper {

	ParametroTipo obterPorId(Long id);
}
