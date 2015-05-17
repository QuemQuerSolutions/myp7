package com.plataforma.myp7.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.DTO.HistoricoSenhaUsuarioLimit;
import com.plataforma.myp7.conexao.Conexao;
import com.plataforma.myp7.data.HistoricoSenha;
import com.plataforma.myp7.data.Usuario;

public class HistoricoSenhaDAO {
	private SqlSession session;
	
	public HistoricoSenhaDAO(){
		this.session = Conexao.getInstance().getSqlSession();
	}
	
	public List<HistoricoSenha> selecionarPorUsuario(Usuario usuario, int qtdLinhas){
		
		HistoricoSenhaUsuarioLimit _historico = new HistoricoSenhaUsuarioLimit();
		_historico.setQtd(qtdLinhas);
		_historico.setUsuario(usuario.getIdUsuario());
		
		List<HistoricoSenha> historicoSenha = this.session.selectList("obterPorUsuarioELimit", _historico);
		
		return historicoSenha;
	}
}
