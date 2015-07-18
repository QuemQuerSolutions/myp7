package com.plataforma.myp7.data;

import org.apache.ibatis.type.Alias;

@Alias("Empresa")
public class Empresa {
	private Long idEmpresa;
	private String nomeReduzido;
	private Pessoa pessoa;
	
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
}
