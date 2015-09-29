package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.RepresentanteFornecedor;

@Component
public interface RepresentanteFornecedorMapper {
	List<RepresentanteFornecedor> obterPorFornecedor(Long idFornecedor);
	void insert(RepresentanteFornecedor representanteFornecedor);
	void deletePorComprador(Long idFornecedor);
}
