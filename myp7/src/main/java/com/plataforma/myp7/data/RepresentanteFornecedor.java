package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("RepresentanteFornecedor")
public class RepresentanteFornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Representante representante;
	private Fornecedor fornecedor;
	private Long id;
	
	public RepresentanteFornecedor(){
		representante = new Representante();
		fornecedor = new Fornecedor();
	}
	
	public RepresentanteFornecedor(Representante representante, Long idFornecedor){
		this.representante = representante;
		this.id = idFornecedor;
	}
	
	public RepresentanteFornecedor(Fornecedor fornecedor, Long idRepresentante){
		this.fornecedor = fornecedor;
		this.id = idRepresentante;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public Long getIdFornecedor() {
		return id;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.id = idFornecedor;
	}

}
