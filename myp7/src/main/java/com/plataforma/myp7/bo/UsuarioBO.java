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
	
	public String inserir(Usuario usuario, Model model) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException{
		ParametroDominio dominio = new ParametroDominio();
		
		if(isDuplicadoEmail(usuario)){
			Utils.setMsgRetorno(model, "Email já existente.");
			model.addAttribute("codMsgem", -1);
			return "components/login";
		}
		dominio.setId(FuncionalidadeEnum.USUARIO_Fun.getCodFunc());
		dominio.setDescricao(FuncionalidadeEnum.USUARIO_Fun.getDescFunc().toString());
		if(!senhaBO.isValido(usuario.getSenha(), usuario, dominio)){
			System.out.println("está  chegando na senha?");
			Utils.setMsgRetorno(model, "A senha deve conter letra número e letra maiúscula.");
			model.addAttribute("codMsgem", -1);
			return "components/login";
		}
		
		usuario.setSenha(CriptografarBO.criptografar(usuario.getSenha().toString()));
		this.usuarioDAO.inserir(usuario);
		Utils.setMsgRetorno(model, "Usuario inserido com sucesso.");
		model.addAttribute("codMsgem", 0);
		return "components/login";
	}	
	
	public boolean isDuplicadoEmail(Usuario usuario){
		return !Objects.isNull(this.usuarioDAO.selecionarPorEmail(usuario.getEmail()));
	}
	
}
