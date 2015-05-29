package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.Embalagem;
import com.plataforma.myp7.dto.ParametrosPesquisaEmbalagens;

public class EmbalagemDAO {
	private SqlSession session;
	
	public EmbalagemDAO(){
		this.session = getConexao();
	}
	
	public List<Embalagem> selecionaPorParametros(ParametrosPesquisaEmbalagens parametros){
		return this.session.selectList("obterEmbalagens", parametros);
	}
	
	public List<Embalagem> selecionaTodos(){
		return this.session.selectList("obterTodasEmbalagens");
	}

	public Integer count(ParametrosPesquisaEmbalagens parametros) {
		return (Integer) this.session.selectOne("countEmbalagem", parametros);
	}
}
