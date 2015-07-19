package com.plataforma.myp7.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Fornecedor;
import com.plataforma.myp7.mapper.FornecedorMapper;

@Service
public class FornecedorBO {

	@Autowired
	private FornecedorMapper fornecedorMapper;
	
	public List<Fornecedor> obterTodos(){
		return this.fornecedorMapper.obterTodos();
	}
	
	public void inserir(Fornecedor fornecedor){
		this.fornecedorMapper.inserir(fornecedor);
	}
}
