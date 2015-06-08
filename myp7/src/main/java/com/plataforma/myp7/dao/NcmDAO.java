package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.NCM;

public class NcmDAO {
	private SqlSession session;
	
	public NcmDAO(){
		this.session = getConexao();
	}
	
	public NCM selecionaPorCodigo(NCM ncmConsulta) {
		return this.session.selectOne("obterNcmPorCodigo", ncmConsulta);
	}
}
