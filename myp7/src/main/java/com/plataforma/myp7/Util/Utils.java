package com.plataforma.myp7.Util;

import org.springframework.ui.Model;

public class Utils {
	
	public static void setMsgRetorno(Model model, final String msg) {
		model.addAttribute("mensagemRetorno", msg);
	}
}
