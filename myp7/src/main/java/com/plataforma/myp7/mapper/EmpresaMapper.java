package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Empresa;

@Component
public interface EmpresaMapper {

	List<Empresa> obterTodasEmpresas();
	List<Empresa> obterEmpresasPorUF(String uf);
	List<Empresa> obterPorComprador(Integer idComprador);

	void inserirEmpresa(Empresa empresa);
	void inserCompradorAlcada(Empresa empresa);
	void updateEmpresa(Empresa empresa);
	void deleteCompradorAlcada(Integer id);

	Empresa obterEmpresaPorId(Long id);
}
