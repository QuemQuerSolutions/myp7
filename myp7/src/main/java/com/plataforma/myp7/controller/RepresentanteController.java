package com.plataforma.myp7.controller;

import static com.plataforma.myp7.util.Utils.emptyToNull;
import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.RepresentanteBO;
import com.plataforma.myp7.bo.UsuarioBO;
import com.plataforma.myp7.data.FornecedorCusto;
import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.util.Utils;

@Controller
@RequestMapping(value={"/retaguarda", "/admin"})
public class RepresentanteController {
	
	@Autowired
	private RepresentanteBO representanteBO;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@RequestMapping("Representante")
	public String inicio(Model model){
		return "RepresentanteLista";
	}
	
	@RequestMapping("carregaListaRepresentante")
	public String carregaListaRepresentante(Representante representante, String origem, Model model, HttpSession session){
		try{
			if ("save".equals(origem)){
				setMsgRetorno(model, Mensagem.SALVO_SUCESSO.getMensagem());
				setCodRetorno(model, Mensagem.SALVO_SUCESSO.getCodigo());
				return "RepresentanteLista";
			}else if("error".equals(origem)){
				setMsgRetorno(model, "Falha na Operação");
				setCodRetorno(model, -1);
				return "RepresentanteLista";
			}
			model.addAttribute("idRepresentante", !Objects.isNull(representante)? representante.getIdRepresentante() : null );
			model.addAttribute("apelido", !Objects.isNull(representante) ? emptyToNull(representante.getApelido().trim()) : null );
			model.addAttribute("razao", !Objects.isNull(representante)? emptyToNull(representante.getRazao().trim()) : null );
			representante.setUsuario(this.usuarioBO.getUserSession(session));
			model.addAttribute("lstRepresentante", this.representanteBO.obterPorParametro(representante, model));
		}catch(Exception e){
			e.printStackTrace();
			setMsgRetorno(model, "Falha na Operação");
			setCodRetorno(model, -1);
		}
		return "RepresentanteLista";
	}	
	
	@RequestMapping(value="obterRepresentantePorParametro", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Representante> obterRepresentantePorParametro(Representante representante, Model model, HttpSession session) {
		if(!Objects.isNull(representante.getParam()) && !Utils.isEmpty(representante.getParam()))
			representante.setUsuario(this.usuarioBO.getUserSession(session));
		return representanteBO.obterPorParametro(representante, model);
	}
	
	@RequestMapping("editarRepresentante")
	public String editarRepresentante(Model model, Long id){
		Representante representante = this.representanteBO.selecionaPorId(id);
		model.addAttribute("qtdFornecedor", representante.getFornecedores().size());
		model.addAttribute("objRepresentante", representante);
		return "RepresentanteSalvar";
	}
	
	
	@RequestMapping("salvarRepresentante")
	public String salvarFornecedor(Representante representante, Model model){
		try{
			this.representanteBO.salvarRepresentante(representante);
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:carregaListaRepresentante?origem=error";
		}
		return "redirect:carregaListaRepresentante?origem=save";
	}
	
	@RequestMapping(value="obterFornecedorCustoPorIdFornecedor", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FornecedorCusto> obterFornecedorPorId(Long id) {
		return representanteBO.obterCustoAprovacaoPorFornecedor(id);
	}
}
