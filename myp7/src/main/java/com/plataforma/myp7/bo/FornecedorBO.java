package com.plataforma.myp7.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Fornecedor;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.exception.ManterEntidadeException;
import com.plataforma.myp7.mapper.FornecedorMapper;

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
			throw new ManterEntidadeException(Mensagem.INSERT_FORNC_ERRO);
		}
	}

	public void update(Fornecedor fornecedor) throws ManterEntidadeException{
		try{
			this.fornecedorMapper.updateFornecedor(fornecedor);
		}catch(Exception e){
			throw new ManterEntidadeException(Mensagem.ATUALIZA_FORNC_ERRO);
		}
	}
}
