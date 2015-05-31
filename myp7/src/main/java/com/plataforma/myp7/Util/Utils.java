package com.plataforma.myp7.Util;

import org.springframework.ui.Model;

public class Utils {
	
	public static void setMsgRetorno(Model model, final String msg) {
		model.addAttribute("mensagemRetorno", msg);
	}
	
	public static void setCodRetorno(Model model, final int cod) {
		model.addAttribute("codMsgem", cod);
	}
	
	public static String emptyToNull(String value){
		return (value.trim().equals("") ? null : value.trim());
	}
}
