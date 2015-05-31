package com.plataforma.myp7.bo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;

import org.springframework.ui.Model;

import com.plataforma.myp7.Util.Utils;
import com.plataforma.myp7.dao.UsuarioDAO;
import com.plataforma.myp7.data.ParametroDominio;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.FuncionalidadeEnum;

public class UsuarioBO {
	

	private UsuarioDAO usuarioDAO;
	
	private SenhaBO senhaBO;
	
	public UsuarioBO(){
		this.usuarioDAO = new UsuarioDAO();
		this.senhaBO = new SenhaBO();
	}
	
	
	public void inserir(Usuario usuario, Model model) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException{

		if(this.isValid(usuario, model)){
			usuario.setSenha(CriptografarBO.criptografar(usuario.getSenha()));
			this.usuarioDAO.inserir(usuario);
			Utils.setMsgRetorno(model, "Usuario inserido com sucesso.");
			Utils.setCodRetorno(model, 0);
		}
	}
	
	private boolean isValid(Usuario usuario, Model model) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException{
		
		if(!Objects.isNull(this.usuarioDAO.selecionarPorEmail(usuario.getEmail()))){
			Utils.setMsgRetorno(model, "Email já cadastrado.");
			Utils.setCodRetorno(model, -1);
			return false;
		}
		
		ParametroDominio dominio = new ParametroDominio();
		dominio.setId(FuncionalidadeEnum.USUARIO_FUN.getCodFunc());
		dominio.setDescricao(FuncionalidadeEnum.USUARIO_FUN.getDescFunc().toString());
		
		if(!this.senhaBO.isValid(usuario.getSenha(), usuario, dominio)){
			Utils.setMsgRetorno(model, "A senha deve conter ao menos uma letra, um número e uma letra maiúscula.");
			Utils.setCodRetorno(model, -1);
			return false;
		}
		
		return true;
	}	
	
}
