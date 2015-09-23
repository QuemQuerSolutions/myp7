package com.plataforma.myp7.data;

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
	
	//Mensagens
	private String msgRetorno;
	private Integer codRetorno;
	
	public Representante(Long idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	public Representante() {
		uuid = UUID.randomUUID().toString();
	}

	public Representante(Mensagem mensagem){
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
