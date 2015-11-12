package com.plataforma.myp7.bo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.ParametroDominio;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.FuncionalidadeEnum;
import com.plataforma.myp7.enums.ThemeEnum;
import com.plataforma.myp7.mapper.UsuarioMapper;

import static com.plataforma.myp7.util.Utils.*;

@Service
public class UsuarioBO {

	@Autowired
	private UsuarioMapper usuarioMapper;
	private static final String ATTR_THEME = "theme";
	
	public Usuario obterPorId(Long id){
		try{
			return this.usuarioMapper.obterPorId(id);
		}catch(Exception e){
			return null;
		}
	}
	
	public Usuario getUserSession(HttpSession session){
		return (Usuario) session.getAttribute(ConfigEnum.USUARIO_LOGADO.getValor());
	}
	
	public void inserir(Usuario usuario, Model model, String tpUsuario) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException{
		if(this.isValidInsert(usuario, model)){
			usuario.setTipoUsuario(tpUsuario);
			usuario.setSenha(CriptografarBO.criptografar(usuario.getSenha()));
			this.usuarioMapper.incluir(usuario);
			setMsgRetorno(model, "Usuario inserido com sucesso.");
			setCodRetorno(model, 0);
		}else{
			setCodRetorno(model, -1);
		}
	}
	
	private boolean isValidInsert(Usuario usuario, Model model) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException{
		
		if(!Objects.isNull(this.usuarioMapper.obterPorEmail(usuario.getEmail()))){
			setMsgRetorno(model, "Email já cadastrado.");
			return false;
		}
		
		ParametroDominio dominio = new ParametroDominio();
		dominio.setId(FuncionalidadeEnum.USUARIO_FUN.getCodFunc());
		dominio.setDescricao(FuncionalidadeEnum.USUARIO_FUN.getDescFunc().toString());

		SenhaBO senhaBO = new SenhaBO();
		if(!senhaBO.isValid(usuario.getSenha(), usuario, dominio)){
			setMsgRetorno(model, "A senha deve conter ao menos uma letra, um número e uma letra maiúscula.");
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
			this.usuarioMapper.updateTheme(usuario);
			
			session.setAttribute(ATTR_THEME, theme);
			setMsgRetorno(model, "Tema alterado com sucesso.");
			setCodRetorno(model, 0);
		}catch(Exception e){
			e.printStackTrace();
			setMsgRetorno(model, "Erro ao alterar tema, contate o administrador.");
			setCodRetorno(model, -1);
		}
	}

	public List<Usuario> selecionaComFiltro(Usuario usuario) {
		if(Objects.isNull(usuario))
			return new ArrayList<Usuario>();
		
		Usuario busca = new Usuario();
		
		busca.setRazaoSocial(usuario.getRazaoSocial().trim().equals("") ? null : toLike(usuario.getRazaoSocial()));
		busca.setEmail(usuario.getEmail().trim().equals("") ? null : toLike(usuario.getEmail()));
		
		return this.usuarioMapper.obterUsuarioComFiltro(busca);
	}
}
