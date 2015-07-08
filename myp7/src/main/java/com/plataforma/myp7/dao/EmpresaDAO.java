package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.Empresa;

public class EmpresaDAO {
	private SqlSession session;
	
	public List<Empresa> selecionaTodos() {
		this.session = getConexao();
		List<Empresa> lista = this.session.selectList("obterTodasEmpresas");
		this.session.commit(true);
		this.session.close();
		return lista;
	}
}
