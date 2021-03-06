package com.plataforma.myp7.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plataforma.myp7.bo.EmpresaBO;
import com.plataforma.myp7.bo.FornecedorCustoBO;
import com.plataforma.myp7.bo.PessoaBO;
import com.plataforma.myp7.bo.RepresentanteBO;
import com.plataforma.myp7.data.FornecedorCusto;
import com.plataforma.myp7.enums.SituacaoIntegracaoEnum;

@Controller
@RequestMapping(value={"/portal", "/admin"})
public class ManutencaoCustosController {

	@Autowired
	private EmpresaBO empresaBO;
	
	@Autowired
	private RepresentanteBO representanteBO;
	
	@Autowired
	private FornecedorCustoBO fornecedorCustoBO;
	
	@Autowired
	private PessoaBO pessoaBO;	
	
	@RequestMapping("ManutencaoCustos")
	public String inicio(HttpSession session, Model model){
		this.carregaComboRepresentantes(model);
		this.carregaComboFiltro(model);
		this.carregaComboUF(model);
		
		return "ManutencaoCustos";
	}
	
	private void carregaComboUF(Model model) {
		model.addAttribute("ufs", this.pessoaBO.selecionaTodasUF());
	}

	private void carregaComboRepresentantes(Model model){
		model.addAttribute("representantes", this.representanteBO.selecionaTodos());
	}
	
	
	private void carregaComboFiltro(Model model){
		Map<Integer, String> filtro = new HashMap<Integer, String>();
		filtro.put(1, "C�digo");
		filtro.put(2, "EAN/DUN");
		
		model.addAttribute("filtros", filtro);
	}
	
	@RequestMapping("consultaManutencaoCusto")
	public @ResponseBody String consultaManutencaoCustoAJAX(String fornecedor, 
															String empresa, 
															String tipo, 
														    String codigo, 
															String descricao) {
		return this.geraTabelaResultado(fornecedorCustoBO.selecionaComFiltro(fornecedor, empresa, tipo, codigo, descricao));
	}
	
	
	@RequestMapping("atuaizaManutencaoCusto")
	public @ResponseBody String atualizaManutencaoCustoAJAX(String id, String novoValor, String valorAnterior) {
		try {
			FornecedorCusto fc = this.fornecedorCustoBO.atualizaManutencaoCusto(id, novoValor, valorAnterior);
			return fc.getValorAnteriorFormatado()+"$"+fc.getValorFormatado();
		} catch (Exception e) {
			return "false";
		}
	}

	private String geraTabelaResultado(List<FornecedorCusto> lista){
		StringBuilder sb = new StringBuilder();
		
		for(FornecedorCusto fc : lista){
			sb.append("<tr>");
			sb.append("		<td>");
			sb.append(			fc.getProduto().getIdProduto());
			sb.append("		</td>");
			sb.append("		<td>");
			sb.append(			fc.getProduto().getDesProduto());
			sb.append("		</td>");
			sb.append("		<td id=\"valorAnterior").append(fc.getIdTabCustoFornecedor()).append("\">");
			sb.append(			fc.getValorAnteriorFormatado());
			sb.append("		</td>");
			sb.append("		<td id=\"valorAtual").append(fc.getIdTabCustoFornecedor()).append("\">");
			sb.append(			fc.getValorFormatado());
			sb.append("		</td>");			
			sb.append("		<td>");
			sb.append("			<input type='text' class='form-control valorNovo' id='valorNovo");
			sb.append(			fc.getIdTabCustoFornecedor());
			sb.append(			"' name='valorNovo' maxlength='10'");
			sb.append(fc.getSituacao().equals(SituacaoIntegracaoEnum.APROVADO.getSigla())?" disabled='disabled' />": " />");
			sb.append("		</td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}
	
}
