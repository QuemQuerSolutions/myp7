package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Forncedor")
public class Fornecedor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idFornecedor;
	private String statusFornecedor;
	private String utilTabCustoFornc;
	
	public Long getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public String getStatusForncedor() {
		return statusFornecedor;
	}
	public void setStatusForncedor(String statusForncedor) {
		this.statusFornecedor = statusForncedor;
	}
	public String getUtilTabCustoFornc() {
		return utilTabCustoFornc;
	}
	public void setUtilTabCustoFornc(String utilTabCustoFornc) {
		this.utilTabCustoFornc = utilTabCustoFornc;
	}
	
	
}
