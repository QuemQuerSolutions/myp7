package com.plataforma.myp7.data;

import java.io.Serializable;
import java.util.UUID;

import org.apache.ibatis.type.Alias;

import com.plataforma.myp7.enums.Mensagem;

@Alias("Empresa")
public class Empresa  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idEmpresa;
	private String nomeReduzido;
	private Integer idCompradorAlcada;
	private Double alcada;
	private Pessoa pessoa;
	private String uuid;
	
	//Mensagens
	private String msgRetorno;
	private Integer codRetorno;
	
	public Empresa(){
		uuid = UUID.randomUUID().toString();
	}
	
	public Empresa(Mensagem mensagem){
		msgRetorno = mensagem.getMensagem();
		codRetorno = mensagem.getCodigo();
	}
	
	public String getUuid() {
		return uuid;
	}

	public String getMsgRetorno() {
		return msgRetorno;
	}

	public Integer getCodRetorno() {
		return codRetorno;
	}

	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}

	public void setCodRetorno(Integer codRetorno) {
		this.codRetorno = codRetorno;
	}

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
