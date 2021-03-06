package com.plataforma.myp7.controller;

import static com.plataforma.myp7.util.Utils.setRetorno;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.UsuarioBO;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.dto.UsuarioDTO;
import com.plataforma.myp7.enums.Mensagem;

@Controller
@RequestMapping(value={"/retaguarda", "/admin"})
public class UsuarioController {
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@RequestMapping("editarUsuario")
	public String inicio(Model model, Long id){
		if(Objects.isNull(id)){
			model.addAttribute("usuario", new Usuario());
			return "UsuarioInserir";
		}
		model.addAttribute("usuario", usuarioBO.obterPorId(id));
		return "UsuarioInserir";
	}
	
	@RequestMapping("cadastroUsuario")
	public String inserir(Usuario usuario, Model model, String tpUsuario){
		String pagina = this.usuarioBO.salvar(usuario, model, tpUsuario);
		model.addAttribute("usuario", usuario);
		if(!"".equals(pagina)){	
			return pagina;
		}
		return "redirect:CarregaListaUsuario?origem=save";
	}

	@RequestMapping(value="ListaUsuario")
	public String pesquisarUsuario() {
		return "UsuarioLista";
	}
	
	@RequestMapping(value="CarregaListaUsuario")
	public String carregaListaUsuario(Model model, UsuarioDTO usuario, String origem) {
		
		if("save".equals(origem)){
			setRetorno(model, Mensagem.SALVO_SUCESSO);
			return "UsuarioLista";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("lstUsuario", this.usuarioBO.selecionaComFiltro(usuario));
		return "UsuarioLista";
	}
	
	@RequestMapping(value="pesquisarUsuarioAJAX", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Usuario> pesquisarUsuarioAJAX(UsuarioDTO usuario) {
		return this.usuarioBO.selecionaComFiltro(usuario);
	}
	
}
