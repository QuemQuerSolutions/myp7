package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.RepresentanteComprador;

@Component
public interface RepresentanteCompradorMapper {
	List<RepresentanteComprador> obterPorComprador(Integer idComprador);
	void insert(RepresentanteComprador representanteComprador);
	void deletePorComprador(Integer idComprador);
	List<RepresentanteComprador> obterPorRepresentante(Long idRepresentante);
}
