package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.Representante;

public class RepresentanteDAO {
	private SqlSession session;
	
	public List<Representante> selecionaTodos() {
		this.session = getConexao();
		List<Representante> lista = this.session.selectList("obterTodosRepresentantes");
		this.session.commit(true);
		this.session.close();
		return lista;
	}
}
