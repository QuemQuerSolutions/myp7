package com.plataforma.myp7.controller;

import static com.plataforma.myp7.util.Utils.format;
import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.FornecedorBO;
import com.plataforma.myp7.bo.UsuarioBO;
import com.plataforma.myp7.data.Fornecedor;
import com.plataforma.myp7.data.FornecedorCusto;
import com.plataforma.myp7.enums.Mensagem;

@Controller
@RequestMapping(value={"/retaguarda", "/admin", "/portal"})
public class FornecedorController {
	
	@Autowired
	private FornecedorBO fornecedorBO;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@RequestMapping("Fornecedor")
	public String inicio(){
		return "FornecedorLista";
	}
	
	@RequestMapping("carregaListaFornecedor")
	public String carregaListaForncedor(Long idFornecedor, String cnpjFornecedor,String razao, String origem, Model model){
		if ("save".equals(origem)){
			setMsgRetorno(model, Mensagem.SALVO_SUCESSO.getMensagem());
			setCodRetorno(model, Mensagem.SALVO_SUCESSO.getCodigo());
			return "FornecedorLista";
		}else if("error".equals(origem)){
			setMsgRetorno(model, "Falha na Opera��o");
			setCodRetorno(model, -1);
			return "FornecedorLista";
		}
		model.addAttribute("idFornecedor", idFornecedor);
		model.addAttribute("cnpj", "".equals(cnpjFornecedor) || Objects.isNull(cnpjFornecedor)? "" :format("##.###.###/####-##", cnpjFornecedor));
		model.addAttribute("razao", razao);
		model.addAttribute("lstFornecedor", this.fornecedorBO.obterFornecedorPorParametro(idFornecedor, cnpjFornecedor,razao, model));
		return "FornecedorLista";
	}
	
	@RequestMapping("editarFornecedor")
	public String editarFornecedor(Model model, Long id){
		Fornecedor fornecedor = this.fornecedorBO.obterPorId(id);
		model.addAttribute("qtdRepresentante", fornecedor.getRepresentantes().size());
		model.addAttribute("objFornecedor", fornecedor);
		return "FornecedorSalvar";
	}
	
	@RequestMapping("salvarFornecedor")
	public String salvarFornecedor(Fornecedor fornecedor, Model model){
		try{
			this.fornecedorBO.salvarFornecedor(fornecedor);
		}catch(Exception e){
			return "redirect:carregaListaFornecedor?origem=error";
		}
		return "redirect:carregaListaFornecedor?origem=save";
	}
	
	@RequestMapping(value="consultarFornecedor", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Set<Fornecedor> consultarFornecedor(Fornecedor fornecedor, HttpSession session){
		return this.fornecedorBO.obterFornecedorPorParametro(fornecedor, this.usuarioBO.getUserSession(session));
	}
	
	@RequestMapping(value="obterFornecedorCustoPorIdRepresentante", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FornecedorCusto> obterFornecedorCustoPorIdRepresentante(Long id) {
		return fornecedorBO.obterCustoAprovacaoPorRepresentante(id);
	}
	
	
	
}
