package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("RepresentanteComprador")
public class RepresentanteComprador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Representante representante;
	private Integer idComprador;
	
	public RepresentanteComprador(){
		representante = new Representante();
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public Integer getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(Integer idComprador) {
		this.idComprador = idComprador;
	}

	
}
