package com.plataforma.myp7.dao;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.data.HistoricoSenha;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.dto.HistoricoSenhaUsuarioLimit;

public class HistoricoSenhaDAO {
	private SqlSession session;
	
	public List<HistoricoSenha> selecionarPorUsuario(Usuario usuario, int qtdLinhas){
		
		HistoricoSenhaUsuarioLimit historico = new HistoricoSenhaUsuarioLimit();
		historico.setQtd(qtdLinhas);
		historico.setUsuario(usuario.getIdUsuario());
		
		this.session = getConexao();
		List<HistoricoSenha> historicoSenha = this.session.selectList("obterPorUsuarioELimit", historico);
		this.session.close();
		
		return historicoSenha;
	}
}
