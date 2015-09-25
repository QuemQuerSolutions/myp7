package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Fornecedor;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.enums.MensagemWS;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.FornecedorMapper;
import com.plataforma.myp7.util.Utils;

@Service
public class FornecedorBO {

	@Autowired
	private FornecedorMapper fornecedorMapper;
	
	public List<Fornecedor> obterTodos(){
		return this.fornecedorMapper.obterTodos();
	}
	
	public void inserir(Fornecedor fornecedor) throws ManterEntidadeException{
		try{
			this.fornecedorMapper.inserirFornecedor(fornecedor);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.INSERT_FORNC_ERRO);
		}
	}

	public void update(Fornecedor fornecedor) throws ManterEntidadeException{
		try{
			this.fornecedorMapper.updateFornecedor(fornecedor);
		}catch(Exception e){
			throw new ManterEntidadeException(MensagemWS.ATUALIZA_FORNC_ERRO);
		}
	}
	
	public List<Fornecedor> obterFornecedorPorParametro(Long idFornecedor, String cnpjFornecedor, Model model){
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setIdFornecedor(idFornecedor);
		
		if (!"".equals(cnpjFornecedor)) 
			fornecedor = this.setNumDigitoDocumento(cnpjFornecedor, fornecedor);
		
		Integer countFornecedor = this.fornecedorMapper.countFornecedorPorParametro(fornecedor);
		
		if(countFornecedor == 0){
			Utils.setMsgRetorno(model, Mensagem.NENHUM_REGISTRO_LOCALIZADO.getMensagem());
			Utils.setCodRetorno(model, Mensagem.NENHUM_REGISTRO_LOCALIZADO.getCodigo());
			return new ArrayList<Fornecedor>();
		}
		if(countFornecedor > ConfigEnum.LIMITE_COUNT.getValorInt()){
			Utils.setMsgRetorno(model, Mensagem.REFINE_SUA_PESQUISA.getMensagem());
			Utils.setCodRetorno(model, Mensagem.REFINE_SUA_PESQUISA.getCodigo());
			return new ArrayList<Fornecedor>();
		}
		return this.formataCNPJ(this.fornecedorMapper.obterFornecedorPorParametro(fornecedor));
	}
	
	private Fornecedor setNumDigitoDocumento(String nDocumento, Fornecedor fornecedor){
		fornecedor.setNroCpfCnpj(nDocumento.substring(0, nDocumento.length()-2));
		fornecedor.setDigCpfCnpj(Integer.parseInt(nDocumento.substring(nDocumento.length()-2, nDocumento.length())));
		return fornecedor;
	}
	
	private List<Fornecedor> formataCNPJ(List<Fornecedor> lstFornecedor){
		String cnpj;
		int posicao;
		List<Fornecedor> lstFornecedorNovo = new ArrayList<Fornecedor>();
		for(Fornecedor f:lstFornecedor){
			cnpj= f.getNroCpfCnpj().concat(String.valueOf(f.getDigCpfCnpj()));
			cnpj= Utils.format("##.###.###/####-##", cnpj);
			posicao = cnpj.indexOf("-");
			f.setNroCpfCnpj(cnpj.substring(0, posicao));
			f.setDigCpfCnpj(Integer.parseInt(cnpj.substring(posicao+1, cnpj.length())));
			lstFornecedorNovo.add(f);
		}
		return lstFornecedorNovo;
	}
	
	
	
	
	
}
