package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;
import java.util.Objects;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.util.Utils;

public class ProdutoDAO {
	private SqlSession session;
	
	public ProdutoDAO(){
		this.session = getConexao();
	}
	
	public List<Produto> obterTodos(){
		this.session = getConexao();
		List<Produto> lista = this.session.selectList("obterTodos");
		this.session.close();
		return lista;
	}
	
	public List<Produto> obterProdutos(Produto produto) throws Exception{
		this.session = getConexao();
		this.setLike(produto);
		List<Produto> lstProduto = this.session.selectList("obterProdutos", produto);
		this.cleanLike(produto);
		this.session.close();
		return lstProduto;
	}
	
	public Integer count(Produto produto) throws Exception{
		this.session = getConexao();
		this.setLike(produto);
		Integer count = (Integer) this.session.selectOne("countProduto", produto);
		this.cleanLike(produto);
		this.session.close();
		return count;
	}
	
	private void setLike(Produto produto){
		produto.setDesProduto(Utils.toLike(produto.getDesProduto()));
	}
	
	private void cleanLike(Produto produto){
		produto.setDesProduto(Utils.cleanLike(produto.getDesProduto()));
		
	}
	
	public Produto obterPorId(Long id){
		this.session = getConexao();
		Produto produto = this.session.selectOne("obterProdutoPorId", id);
		this.session.close();
		return produto;
	}

	public void salvar(final Produto produto) {
		this.session = getConexao();
		if(!Objects.isNull(produto.getIdProduto()) && produto.getIdProduto() != 0L){
			this.session.update("atualizaProduto", produto);
		}else{
			this.session.insert("salvarProduto", produto);
		}
		this.session.commit(true);
		this.session.close();
	}
	
}
