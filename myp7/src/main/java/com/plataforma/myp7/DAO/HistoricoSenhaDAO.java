package com.plataforma.myp7.DAO;

import static com.plataforma.myp7.config.Conexao.getConexao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.DTO.HistoricoSenhaUsuarioLimit;
import com.plataforma.myp7.data.HistoricoSenha;
import com.plataforma.myp7.data.Usuario;

public class HistoricoSenhaDAO {
	private SqlSession session;
	
	public HistoricoSenhaDAO(){
		this.session = getConexao();
	}
	
	public List<HistoricoSenha> selecionarPorUsuario(Usuario usuario, int qtdLinhas){
		
		HistoricoSenhaUsuarioLimit historico = new HistoricoSenhaUsuarioLimit();
		historico.setQtd(qtdLinhas);
		historico.setUsuario(usuario.getIdUsuario());
		
		List<HistoricoSenha> historicoSenha = this.session.selectList("obterPorUsuarioELimit", historico);
		
		return historicoSenha;
	}
}
