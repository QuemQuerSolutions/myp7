package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Empresa;

@Component
public interface EmpresaMapper {

	List<Empresa> obterTodasEmpresas();
	
	List<Empresa> obterEmpresasPorUF(String uf);

	void inserirEmpresa(Empresa empresa);

	void updateEmpresa(Empresa empresa);

	Empresa obterEmpresaPorId(Long id);
}
