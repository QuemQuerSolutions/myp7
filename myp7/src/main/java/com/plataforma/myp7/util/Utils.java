package com.plataforma.myp7.util;

import java.io.File;
import java.util.Objects;

import javax.servlet.http.HttpSession;
import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;

import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;

public class Utils {
	
	private final static Logger log = Logger.getLogger(Utils.class);
	
	public static String format(String formato, Object valor){
		MaskFormatter mascara = null;
		try{
			mascara = new MaskFormatter(formato);
			mascara.setValueContainsLiteralCharacters(false);
			return mascara.valueToString(valor);
		}catch(Exception e){
			log.error("Utils.format", e);
			throw new RuntimeException(e);
		}
	}
	
	public static boolean isEmpty(String string){
		return string == null || string.trim().equals("");
	}
	
	public static void setMsgRetorno(Model model, final String msg) {
		model.addAttribute("mensagemRetorno", msg);
	}
	
	public static void setCodRetorno(Model model, final int cod) {
		model.addAttribute("codMsgem", cod);
	}
	
	public static void setRetorno(Model model, Mensagem mensagem){
		model.addAttribute("mensagemRetorno", mensagem.getMensagem());
		model.addAttribute("codMsgem", mensagem.getCodigo());
	}
	
	public static String emptyToNull(String value){
		return ("".equals(value) ? null : value);
	}
	
	public static String toLike(String campo){
		campo = emptyToNull(campo);
		return (!Objects.isNull(campo) ? String.format("%s%s%s", "%", campo, "%") : campo);
	}
	
	public static String cleanLike(String campo){
		return (Objects.isNull(campo) ? null : campo.replaceAll("%", ""));
	}
	

	public static String getExtensaoArq(String arquivo){
		return arquivo.substring(arquivo.lastIndexOf('.'), arquivo.length());
	}
	
	public static void removeArquivo(HttpSession session, String caminhoImagem){
		String diretorio = session.getServletContext().getRealPath(ConfigEnum.FOLDER_UPLOAD_DEFAULT.getValor());
		new File(diretorio+"\\"+caminhoImagem).delete();
	}
	
	public static void verificaPastaExistente(String caminho){
		File file = new File(caminho);
	    
		if(!file.exists())
			file.mkdirs();
	}
	
}
