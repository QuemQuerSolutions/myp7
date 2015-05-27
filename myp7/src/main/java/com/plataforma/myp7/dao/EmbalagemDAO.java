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
		if(!parametros.getSigla().equals("") && !parametros.getDescricao().equals("")){
			return this.session.selectList("obterEmbalagensPorSiglaDescricao", parametros);
		}else if(!parametros.getSigla().equals("")){
			return this.session.selectList("obterEmbalagensPorSigla", parametros.getSigla());
		}else if(!parametros.getDescricao().equals("")){
			return this.session.selectList("obterEmbalagensPorDescricao", parametros.getDescricao());
		}
		
		return null;
	}
	
	public List<Embalagem> selecionaTodos(){
		return this.session.selectList("obterTodasEmbalagens");
	}
}
