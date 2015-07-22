package com.plataforma.myp7.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.FornecedorCusto;
import com.plataforma.myp7.mapper.FornecedorCustoMapper;

@Service
public class FornecedorCustoBO {

	@Autowired
	private FornecedorCustoMapper fornecedorCustoMapper;
	
	public List<FornecedorCusto> seleciona() {
		return this.fornecedorCustoMapper.obterTodos();
	}
	
	public List<FornecedorCusto> selecionaComFiltro(FornecedorCusto fc) {
		return this.fornecedorCustoMapper.obterComFiltro(fc);
	}	

}
