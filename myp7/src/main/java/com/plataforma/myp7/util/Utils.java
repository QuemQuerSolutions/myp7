package com.plataforma.myp7.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;

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
	
	public static String toLike(String campo){
		return (!Objects.isNull(campo) ? String.format("%s%s%s", "%", campo, "%") : campo);
	}
	
	public static String cleanLike(String campo){
		return (Objects.isNull(campo) ? null : campo.replaceAll("%", ""));
	}
	
	public static String getDataAtualString(String formato) {
		return new SimpleDateFormat(formato).format(new Date());
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
	
	public static MensagemRetorno formataMsgem(Integer cod){
		MensagemRetorno msgem = new MensagemRetorno();
		for(Mensagem msg:Mensagem.values()){
			if (cod==msg.getCodigo()){
				msgem.setCodRetorno(msg.getCodigo());
				msgem.setMsgRetorno(msg.getMensagem());
			}
		}
		
		return msgem;
	}
}
