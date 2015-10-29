package com.plataforma.myp7.exception;

import java.sql.SQLException;

import com.plataforma.myp7.enums.MensagemWS;

public class ManterEntidadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private MensagemWS mensagemEnum;
	
	public ManterEntidadeException(MensagemWS message) {
		super(message.getMensagem(), new SQLException());
		this.mensagemEnum = message;
	}

	public String getMessage(){
		return this.mensagemEnum.getMensagem();
	}
	
	public MensagemWS getMensagemEnum(){
		return this.mensagemEnum;
	}	
}
