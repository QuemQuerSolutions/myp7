package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.Embalagem;

public class EmbalagemDAO {
	private SqlSession session;
	
	public EmbalagemDAO(){
		this.session = getConexao();
	}
	
	public List<Embalagem> selecionaPorParametros(Embalagem embalagem){
		return this.session.selectList("obterEmbalagens", embalagem);
	}
	
	public List<Embalagem> selecionaTodos(){
		return this.session.selectList("obterTodasEmbalagens");
	}

	public Integer count(Embalagem embalagem) {
		return (Integer) this.session.selectOne("countEmbalagem", embalagem);
	}

	public void salvar(Embalagem embalagem) throws Exception {
		this.session.selectOne("salvarEmbalagem", embalagem);
		this.session.commit(true);
	}
}
