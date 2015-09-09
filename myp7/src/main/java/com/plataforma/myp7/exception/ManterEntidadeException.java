package com.plataforma.myp7.exception;

import com.plataforma.myp7.enums.Mensagem;

public class ManterEntidadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Mensagem mensagemEnum;
	
	public ManterEntidadeException(Mensagem message) {
		super(message.getMensagem(), new Exception());
		this.mensagemEnum = message;
	}

	public String getMessage(){
		return this.mensagemEnum.getMensagem();
	}
	
	public Mensagem getMensagemEnum(){
		return this.mensagemEnum;
	}	
}
