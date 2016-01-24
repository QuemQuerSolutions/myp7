package com.plataforma.myp7.dto;

import java.util.List;

public class AprovaDTO {
	private Long valor;
	
	private List<Long> idFornecedorCustoAprova;

	public AprovaDTO(){
	}
	
	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public List<Long> getIdFornecedorCustoAprova() {
		return idFornecedorCustoAprova;
	}

	public void setIdFornecedorCustoAprova(List<Long> idFornecedorCustoAprova) {
		this.idFornecedorCustoAprova = idFornecedorCustoAprova;
	}
	
	
}
