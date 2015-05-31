package com.plataforma.myp7.enums;

public enum FuncionalidadeEnum {
	USUARIO_FUN(1,"Usuario");
	
	private String descFunc;
	private Integer codFunc;
	
	
	private FuncionalidadeEnum(Integer codFunc, String descFunc) {
		this.descFunc = descFunc;
		this.codFunc = codFunc;
	}
	public String getDescFunc() {
		return descFunc;
	}
	public void setDescFunc(String descFunc) {
		this.descFunc = descFunc;
	}
	public Integer getCodFunc() {
		return codFunc;
	}
	public void setCodFunc(Integer codFunc) {
		this.codFunc = codFunc;
	}
	
	
}
