package com.plataforma.myp7.dto;

public class MensagemRetornoDTO {
	private String MsgRetorno;
	private Integer codRetorno;
	
	
	public String getMsgRetorno() {
		return MsgRetorno;
	}
	public void setMsgRetorno(String msgRetorno) {
		MsgRetorno = msgRetorno;
	}
	public Integer getCodRetorno() {
		return codRetorno;
	}
	public void setCodRetorno(Integer codRetorno) {
		this.codRetorno = codRetorno;
	}
	
}