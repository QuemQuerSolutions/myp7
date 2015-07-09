package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.data.NCM;
import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.data.Usuario;

@Component
public interface ProdutoMapper {

	Produto obterProdutoPorId(Long idProduto);
	
	List<Produto> obterTodos();
	
	List<Produto> obterProdutos(Produto produto);
	
	int countProduto(Produto produto);
	
	void salvarProduto(Produto produto);
	
	void atualizaProduto(Produto produto);
	
	NCM obterNCMporId(Produto produto);
	
	Usuario obterUsuarioPorId(Produto produto);
	
	Embalagem obterEmbalagemPorIdP(Produto produto);
	
	
}
