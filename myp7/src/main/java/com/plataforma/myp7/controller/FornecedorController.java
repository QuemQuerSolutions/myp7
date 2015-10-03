package com.plataforma.myp7.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plataforma.myp7.bo.FornecedorBO;
import com.plataforma.myp7.data.Fornecedor;
import com.plataforma.myp7.enums.Mensagem;
import static com.plataforma.myp7.util.Utils.*;

@Controller
public class FornecedorController {
	
	@Autowired
	private FornecedorBO fornecedorBO;
	
	@RequestMapping("Fornecedor")
	public String inicio(){
		return "FornecedorLista";
	}
	
	@RequestMapping("carregaListaFornecedor")
	public String carregaListaForncedor(Long idFornecedor, String cnpjFornecedor,String razao, String origem, Model model){
		try{
			if ("save".equals(origem)){
				setMsgRetorno(model, Mensagem.SALVO_SUCESSO.getMensagem());
				setCodRetorno(model, Mensagem.SALVO_SUCESSO.getCodigo());
				return "FornecedorLista";
			}
			model.addAttribute("idFornecedor", idFornecedor);
			model.addAttribute("cnpj", "".equals(cnpjFornecedor) || Objects.isNull(cnpjFornecedor)? "" :format("##.###.###/####-##", cnpjFornecedor));
			model.addAttribute("razao", razao);
			model.addAttribute("lstFornecedor", this.fornecedorBO.obterFornecedorPorParametro(idFornecedor, cnpjFornecedor,razao, model));
		}catch(Exception e){
			setMsgRetorno(model, "Falha na Operação");
			setCodRetorno(model, -1);
		}
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
			e.printStackTrace();
			setMsgRetorno(model, "Falha na Operação");
			setCodRetorno(model, -1);
		}
		return "redirect:carregaListaFornecedor?origem=save";
	}
	
}
