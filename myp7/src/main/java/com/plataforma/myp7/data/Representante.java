package com.plataforma.myp7.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.type.Alias;

import com.plataforma.myp7.bo.UsuarioBO;
import com.plataforma.myp7.enums.Mensagem;

@Alias("Representante")
public class Representante {
	private Long idRepresentante;
	private String apelido;
	private String status;
	private Usuario usuario;
	private String uuid;
	private List<Fornecedor> fornecedores;
	private String razao;
	
	//Mensagens
	private String msgRetorno;
	private Integer codRetorno;
	
	public Representante(Long idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	public Representante() {
		uuid = UUID.randomUUID().toString();
		this.fornecedores = new ArrayList<Fornecedor>();
	}

	public Representante(Mensagem mensagem){
		msgRetorno = mensagem.getMensagem();
		codRetorno = mensagem.getCodigo();
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
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

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void setUsuario(Long idUsuario) {
		this.usuario = new UsuarioBO().obterPorId(idUsuario);
	}

	public Long getIdRepresentante() {
		return idRepresentante;
	}
	
	public void setIdRepresentante(Long idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	public void setIdRepresentante(String idRepresentante) {
		try{
			this.idRepresentante = Long.parseLong(idRepresentante);
		}catch(Exception e){ }
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razaoSocial) {
		this.razao = razaoSocial;
	}
}
