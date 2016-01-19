package com.plataforma.myp7.util;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.enums.ConfigEnum;

public class Upload {

	private final static Logger log = Logger.getLogger(Upload.class);
	
	/**
	 *  Efetua o upload da maquina do usuario para o servidor
	 * @param request Usado para pegar o contexto e o caminho da aplicacao
	 * @param file Arquivo a ser efeutado o upload
	 * @return
	 */
	public File armazenar(HttpSession session, Produto produto) {
		
		try {
			byte[] bytes = produto.getImagem().getBytes();
			
			String dataHoraAtual = DateUtils.getDataAtualString("yyyyMMddHHmmss");
			
			//verifica se nao teve nenhum input de imagem
			if(produto.getImagem().getOriginalFilename().equals("")) 
				return new File("");
			
			String dir = session.getServletContext().getRealPath(ConfigEnum.FOLDER_UPLOAD_DEFAULT.getValor());
			Utils.verificaPastaExistente(dir);
			
			
			String nomeArquivo = StringUtils.concat(dir, File.separator, produto.getIdProduto(), dataHoraAtual, Utils.getExtensaoArq(produto.getImagem().getOriginalFilename()));
			File arq = new File(nomeArquivo);
			
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(arq));
            stream.write(bytes);
            stream.close();
            
			return arq;

		} catch (Exception e) {
			log.error("Upload.armazenar", e);
			return null;
		}
	}
	
	public void removerArqTmp(File arquivo){
		arquivo.delete();
	}
}