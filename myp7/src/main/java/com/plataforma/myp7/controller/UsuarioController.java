package com.plataforma.myp7.controller;

import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.UsuarioBO;
import com.plataforma.myp7.data.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@RequestMapping("Usuario")
	public String inicio(Model model){
		return "UsuarioInserir";
	}
	
	@RequestMapping("cadastroUsuario")
	public String inserir(Usuario usuario, Model model, String tpUsuario){
		try {
			this.usuarioBO.inserir(usuario, model, tpUsuario);
		} catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			setMsgRetorno(model, "Falha na operação.");
			setCodRetorno(model, -1);
		}
		return "UsuarioInserir";
	}

	@RequestMapping(value="ListaUsuario")
	public String pesquisarUsuario() {
		return "UsuarioLista";
	}
	
	@RequestMapping(value="CarregaListaUsuario")
	public String carregaListaUsuario(Model model, Usuario usuario) {
		model.addAttribute("usuario", usuario);
		model.addAttribute("lstUsuario", this.usuarioBO.selecionaComFiltro(usuario));
		return "UsuarioLista";
	}
	
	@RequestMapping(value="pesquisarUsuarioAJAX", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Usuario> pesquisarUsuarioAJAX(Usuario usuario) {
		return this.usuarioBO.selecionaComFiltro(usuario);
	}
	
}
