package com.plataforma.myp7.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.plataforma.myp7.DTO.HistoricoSenha_Usuario_Limit;
import com.plataforma.myp7.conexao.Conexao;
import com.plataforma.myp7.data.HistoricoSenha;
import com.plataforma.myp7.data.Usuario;

public class HistoricoSenhaDAO {
	private SqlSession session;
	
	public HistoricoSenhaDAO(){
		this.session = Conexao.getInstance().getSqlSession();
	}
	
	public List<HistoricoSenha> selecionarPorUsuario(Usuario usuario, int qtdLinhas){
		
		HistoricoSenha_Usuario_Limit historicoSenha_Usuario_Limit = new HistoricoSenha_Usuario_Limit();
		historicoSenha_Usuario_Limit.setQtd(qtdLinhas);
		historicoSenha_Usuario_Limit.setUsuario(usuario.getIdUsuario());
		
		List<HistoricoSenha> historicoSenha = this.session.selectList("obterPorUsuarioELimit", historicoSenha_Usuario_Limit);
		
		return historicoSenha;
	}
}
