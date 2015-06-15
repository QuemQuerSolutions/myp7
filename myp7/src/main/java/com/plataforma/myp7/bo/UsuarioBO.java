package com.plataforma.myp7.bo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.plataforma.myp7.dao.UsuarioDAO;
import com.plataforma.myp7.data.ParametroDominio;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.FuncionalidadeEnum;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.ThemeEnum;
import com.plataforma.myp7.util.Utils;

public class UsuarioBO {
	
	private UsuarioDAO usuarioDAO;
	private SenhaBO senhaBO;
	private static final String ATTR_THEME = "theme";
	
	public UsuarioBO(){
		this.usuarioDAO = new UsuarioDAO();
		this.senhaBO = new SenhaBO();
	}
	
	public void inserir(Usuario usuario, Model model) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException{
		if(this.isValidInsert(usuario, model)){
			usuario.setSenha(CriptografarBO.criptografar(usuario.getSenha()));
			this.usuarioDAO.inserir(usuario);
			Utils.setMsgRetorno(model, "Usuario inserido com sucesso.");
			Utils.setCodRetorno(model, 0);
		}else{
			Utils.setCodRetorno(model, -1);
		}
	}
	
	private boolean isValidInsert(Usuario usuario, Model model) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException{
		
		if(!Objects.isNull(this.usuarioDAO.selecionarPorEmail(usuario.getEmail()))){
			Utils.setMsgRetorno(model, "Email já cadastrado.");
			return false;
		}
		
		ParametroDominio dominio = new ParametroDominio();
		dominio.setId(FuncionalidadeEnum.USUARIO_FUN.getCodFunc());
		dominio.setDescricao(FuncionalidadeEnum.USUARIO_FUN.getDescFunc().toString());
		
		if(!this.senhaBO.isValid(usuario.getSenha(), usuario, dominio)){
			Utils.setMsgRetorno(model, "A senha deve conter ao menos uma letra, um número e uma letra maiúscula.");
			return false;
		}
		
		return true;
	}	
	
	public void updateTheme(String theme, Model model, HttpSession session) {
		try{
			Usuario usuario = new Usuario();
			usuario.setTheme(ThemeEnum.getValorBD(theme));
			Usuario userSession = (Usuario) session.getAttribute(ConfigEnum.USUARIO_LOGADO.getValor());
			usuario.setIdUsuario(userSession.getIdUsuario());
			this.usuarioDAO.updateTheme(usuario);
			
			session.setAttribute(ATTR_THEME, theme);
			Utils.setMsgRetorno(model, "Tema alterado com sucesso.");
			Utils.setCodRetorno(model, 0);
		}catch(SQLException e){
			e.printStackTrace();
			Utils.setMsgRetorno(model, "Erro ao alterar tema, contate o administrador.");
			Utils.setCodRetorno(model, -1);
		}
	}
}
