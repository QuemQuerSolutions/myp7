package com.plataforma.myp7.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.RelatorioEstoque;
import com.plataforma.myp7.interfaces.ComboPessoa;
import com.plataforma.myp7.mapper.RelatorioEstoqueMapper;

@Service
public class RelatorioEstoqueBO {

	@Autowired
	private RelatorioEstoqueMapper relatorioEstoqueMapper;
	
	public List<RelatorioEstoque> obterPorParametros(Produto produto, ComboPessoa pessoa){
		try{
			return this.relatorioEstoqueMapper.obterPorParametros(produto, pessoa);
		}catch(Exception e){
			return null;
		}
	}
}
