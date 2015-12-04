package com.plataforma.myp7.mapper;

import java.util.List;

import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.RelatorioEstoque;
import com.plataforma.myp7.interfaces.ComboPessoa;

public interface RelatorioEstoqueMapper {

	public List<RelatorioEstoque> obterPorParametros(Produto produto, ComboPessoa pessoa);
}
