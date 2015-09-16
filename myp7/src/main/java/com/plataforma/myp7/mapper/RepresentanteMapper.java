package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Representante;

@Component
public interface RepresentanteMapper {

	List<Representante> obterTodosRepresentantes();

	void updateRepresentante(Representante representante);

	void insertRepresentante(Representante representante);

	Representante obterPorId(Long idRepresentante);
}
