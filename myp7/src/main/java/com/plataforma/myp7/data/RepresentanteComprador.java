package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("RepresentanteComprador")
public class RepresentanteComprador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Representante representante;
	private Comprador comprador;
	private Long idRepresentante;
	private Integer idComprador;
	
	public RepresentanteComprador(Representante representante, Integer idComprador){
		this.representante = representante;
		this.idComprador = idComprador;
	}
	
	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public Long getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Long idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

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
