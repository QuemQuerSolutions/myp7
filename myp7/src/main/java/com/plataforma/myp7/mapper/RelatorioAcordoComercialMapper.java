package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.RelatorioAcordoComercial;

@Component
public interface RelatorioAcordoComercialMapper {
	public List<RelatorioAcordoComercial> obterPorParametro(RelatorioAcordoComercial relatorioAcordoComercial);
}
