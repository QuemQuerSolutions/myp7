package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Fornecedor")
public class Fornecedor extends Pessoa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idFornecedor;
	private String statusFornecedor;
	private String utilTabCustoFornc;
	private String cnpjFormatado;
	
	public Fornecedor(){
		super();
	}
	
	public Long getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public String getStatusFornecedor() {
		return statusFornecedor;
	}
	public void setStatusFornecedor(String statusFornecedor) {
		this.statusFornecedor = statusFornecedor;
	}
	public String getUtilTabCustoFornc() {
		return utilTabCustoFornc;
	}
	public void setUtilTabCustoFornc(String utilTabCustoFornc) {
		this.utilTabCustoFornc = utilTabCustoFornc;
	}

	public String getCnpjFormatado() {
		return cnpjFormatado;
	}

	public void setCnpjFormatado(String cnpjFormatado) {
		this.cnpjFormatado = cnpjFormatado;
	}
	
	
}
