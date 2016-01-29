package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;
import static com.plataforma.myp7.util.Utils.toLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.ParametroDominio;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.dto.UsuarioDTO;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.FuncionalidadeEnum;
import com.plataforma.myp7.enums.ThemeEnum;
import com.plataforma.myp7.mapper.UsuarioMapper;
import com.plataforma.myp7.util.Utils;

@Service
public class UsuarioBO {
	
	private final static Logger log = Logger.getLogger(UsuarioBO.class);

	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private SenhaBO senhaBO;
	
	private static final String ATTR_THEME = "theme";
	
	public Usuario obterPorId(Long id){
		try{
			return this.usuarioMapper.obterPorId(id);
		}catch(Exception e){
			log.error("UsuarioBO.obterPorId", e);
			return null;
		}
	}
	
	public Usuario getUserSession(HttpSession session){
		return (Usuario) session.getAttribute(ConfigEnum.USUARIO_LOGADO.getValor());
	}
	
	public String salvar(Usuario usuario, Model model, String tpUsuario){
		try {
			String pagina="";
			if(Objects.isNull(usuario.getIdUsuario())){
				if(this.isValidInsert(usuario, model)){
					usuario.setTipoUsuario(tpUsuario);
					usuario.setSenha(CriptografarBO.criptografar(usuario.getSenha()));
					this.usuarioMapper.incluir(usuario);
				}else{
					setCodRetorno(model, -1);
					pagina="UsuarioInserir";
				}
				
			}else{
				usuario.setTipoUsuario(tpUsuario);
				usuario.setSenha(CriptografarBO.criptografar(usuario.getSenha()));
				this.usuarioMapper.update(usuario);
				
				removerHierarquiaDosSubordinadosCasoInativacao(usuario);
			}
			return pagina;
		} catch (Exception e) {
			log.error("UsuarioBO.salvar", e);
			return null;
		}
	}
	
	private boolean isValidInsert(Usuario usuario, Model model){
		try {
			if(!Objects.isNull(this.usuarioMapper.obterPorEmail(usuario.getEmail()))){
				setMsgRetorno(model, "Email já cadastrado.");
				return false;
			}
			
			ParametroDominio dominio = new ParametroDominio();
			dominio.setId(FuncionalidadeEnum.USUARIO_FUN.getCodFunc());
			dominio.setDescricao(FuncionalidadeEnum.USUARIO_FUN.getDescFunc().toString());

			if(!senhaBO.isValid(usuario.getSenha(), usuario, dominio)){
				setMsgRetorno(model, "A senha deve conter ao menos uma letra, um número e uma letra maiúscula.");
				return false;
			}
			
			return true;
		} catch (Exception e) {
			log.error("UsuarioBO.isValidInsert", e);
			return false;
		}
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
			setMsgRetorno(model, "Erro ao alterar tema, contate o administrador.");
			setCodRetorno(model, -1);
			log.error("UsuarioBO.updateTheme", e);
		}
	}

	public List<Usuario> selecionaComFiltro(UsuarioDTO usuario) {
		try {
			if(Objects.isNull(usuario))
				return new ArrayList<Usuario>();
			
			usuario.setRazaoSocial(usuario.getRazaoSocial() != null ? usuario.getRazaoSocial().trim().equals("") ? null : toLike(usuario.getRazaoSocial()) : null);
			usuario.setEmail(usuario.getEmail() != null ? usuario.getEmail().trim().equals("") ? null : toLike(usuario.getEmail()) : null);
			
			if(usuario.getIdsUsuarioRemoverLista() != null)
				this.populaListaIds(usuario);
			
			return this.usuarioMapper.obterUsuarioComFiltro(usuario);
		} catch (Exception e) {
			log.error("UsuarioBO.selecionaComFiltro", e);
			return null;
		}
	}
	
	public void inactivateUsuario(Usuario usuario, String status){
		try {
			usuario.setAtivo(status.equals("I")? "0":"1");
			this.usuarioMapper.updateStatus(usuario);
			
			removerHierarquiaDosSubordinadosCasoInativacao(usuario);
		} catch (Exception e) {
			log.error("UsuarioBO.inactivateUsuario", e);
		}
	}

	private void removerHierarquiaDosSubordinadosCasoInativacao(Usuario usuario) {
		if(usuario.getAtivo() != null && usuario.getAtivo().equals("0")){
			UsuarioDTO usuarioDto = new UsuarioDTO();
			usuarioDto.setIdSuperior(usuario.getIdUsuario());
			this.usuarioMapper.updateHierarquiaGeral(usuarioDto);
		}
	}
	
	private void populaListaIds(UsuarioDTO usuario) {
		String[] ids = usuario.getIdsUsuarioRemoverLista().split(",");
		List<Long> list = new ArrayList<>();
		for(String id : ids)
			list.add(Long.parseLong(id));
		usuario.setListIdsUsuarioRemoverLista(list);
	}

	public void salvarHierarquia(UsuarioDTO superior) {
		this.usuarioMapper.updateHierarquia(superior);
		List<Long> listIds = new ArrayList<>();
		
		if(!Utils.isEmpty(superior.getIdsUsuarioParametrosSubordinados())){
			String[] subordinado = superior.getIdsUsuarioParametrosSubordinados().split(";");
			UsuarioDTO subordinadoDTO;
			for(String dados : subordinado){
				subordinadoDTO = new UsuarioDTO();
				String[] itens = dados.split(",");
				
				subordinadoDTO.setIdSuperior	(superior.getIdUsuario());
				subordinadoDTO.setIdUsuario		(itens[0] != null ? Long.parseLong(itens[0]) : null);
				subordinadoDTO.setAprProd		(itens[1] != null ? itens[1].equalsIgnoreCase("TRUE") ? 1 : 0 : null);
				subordinadoDTO.setAprCustoBoo	(itens[2] != null ? Boolean.parseBoolean(itens[2]) : null);
				if(itens.length > 3)
					subordinadoDTO.setValorAlcada	(itens[3] != null ? Integer.parseInt(itens[3]) : null);
				
				this.usuarioMapper.updateHierarquia(subordinadoDTO);
				listIds.add(subordinadoDTO.getIdUsuario());
			}
		}
		
		UsuarioDTO controle = new UsuarioDTO();
		controle.setIdSuperior(superior.getIdUsuario());
		controle.setAprCustoBoo(true);
		controle.setAprProdBoo(true);
		if(listIds != null && listIds.size() > 0){
			controle.setListIdsUsuarioRemoverLista(listIds);
			controle.setIdsUsuarioRemoverLista("Controle");
		}
		this.usuarioMapper.updateHierarquiaGeral(controle);
	}
	
	public Boolean validarSubordinados(Usuario usuario){
		List<Usuario> subordinados = this.usuarioMapper.verificarSubordinados(usuario);
		return subordinados != null ? subordinados.size() == 0 : true;
	}
}
