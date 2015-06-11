package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.NCM;

public class NcmDAO {
	private SqlSession session;
	
	public NCM selecionaPorCodigo(NCM ncmConsulta) {
		this.session = getConexao();
		NCM ncm = this.session.selectOne("obterNcmPorCodigo", ncmConsulta);
		this.session.commit(true);
		this.session.close();
		return ncm;
	}
	
	public NCM obterNCMPorId(Long id){
		this.session = getConexao();
		NCM ncm = this.session.selectOne("obterNcmPorId", id);
		this.session.commit(true);
		this.session.close();
		return ncm;
	}
}
