package com.plataforma.myp7.enums;


public enum ThemeEnum {

	THEME_ORANGE("ORANGE","theme-orange"),
	THEME_BLUE("BLUE","theme-blue"),
	THEME_GREY("GREY","theme-grey"),
	THEME_RED("RED","theme-red"),
	THEME_GREEN("GREEN","theme-green");
	
	private String valorBD;
	private String valorCSS;
	
	private ThemeEnum (String valorBD, String valorCSS){
		this.valorBD = valorBD;
		this.valorCSS = valorCSS;
	}

	public String getValorBD() { return valorBD; }

	public String getValorCSS() { return valorCSS;	}
	
	public static String getValorCSS(String valorDB) { 
		for(ThemeEnum e : ThemeEnum.values()){
			if(valorDB.equals(e.getValorBD())){
				return e.getValorCSS();
			}
		}
		return ConfigEnum.THEME_DEFAULT.getValor();	
	}
	
	public static String getValorBD(String valorCSS) {
		for(ThemeEnum e : ThemeEnum.values()){
			if(valorCSS.equals(e.getValorCSS())){
				return e.getValorBD();
			}
		}
		return null;	
	}

}
