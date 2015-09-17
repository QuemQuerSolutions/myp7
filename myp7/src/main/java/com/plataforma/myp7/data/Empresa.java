package com.plataforma.myp7.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Empresa")
public class Empresa  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idEmpresa;
	private String nomeReduzido;
	private Integer idCompradorAlcada;
	private Double alcada;
	private Pessoa pessoa;
	
	public Empresa(){}
	
	public Integer getIdCompradorAlcada() {
		return idCompradorAlcada;
	}

	public void setIdCompradorAlcada(Integer idCompradorAlcada) {
		this.idCompradorAlcada = idCompradorAlcada;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public String getNomeReduzido() {
		return nomeReduzido;
	}
	
	public void setNomeReduzido(String nomeReduzido) {
		this.nomeReduzido = nomeReduzido;
	}
	
	public Pessoa getIdPessoa() {
		return pessoa;
	}
	
	public void setIdPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Double getAlcada() {
		return alcada;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setAlcada(Double alcada) {
		this.alcada = alcada;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
