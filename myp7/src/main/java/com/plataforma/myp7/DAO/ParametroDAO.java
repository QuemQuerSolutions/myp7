package com.plataforma.myp7.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.conexao.Conexao;
import com.plataforma.myp7.data.DominioParametro;
import com.plataforma.myp7.data.Parametro;

public class ParametroDAO {
	private SqlSession session;
	
	public ParametroDAO(){
		this.session = Conexao.getInstance().getSqlSession();
	}
	
	public List<Parametro> selecionarPorDominio(DominioParametro dominio){
		
		List<Parametro> parametros = this.session.selectList("obterPorDominio", dominio.getId());
		
		return parametros;
	}
}
