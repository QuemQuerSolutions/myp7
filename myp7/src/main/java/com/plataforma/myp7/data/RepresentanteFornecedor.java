package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("RepresentanteFornecedor")
public class RepresentanteFornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Representante representante;
	private Long idFornecedor;
	
	public RepresentanteFornecedor(){
		representante = new Representante();
	}
	
	public RepresentanteFornecedor(Representante representante, Long idFornecedor){
		this.representante = representante;
		this.idFornecedor = idFornecedor;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	

	
}
