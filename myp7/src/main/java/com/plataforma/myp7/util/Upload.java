package com.plataforma.myp7.util;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import com.plataforma.myp7.data.Produto;

public class Upload {

	/**
	 *  Efetua o upload da m�quina do usu�rio para o servidor
	 * @param request Usado para pegar o contexto e o caminho da aplica��o
	 * @param file Arquivo a ser efeutado o upload
	 * @return
	 */
	public File armazenar(HttpSession session, Produto produto) {
		
		try {
			byte[] bytes = produto.getImagem().getBytes();
			String dataHoraAtual = Utils.getDataAtualString("yyyyMMddHHmmss");

			String dir = session.getServletContext().getRealPath("resources/upload");
			String nomeArquivo = dir + File.separator + produto.getCodIndustria() + "_" + dataHoraAtual + Utils.getExtensaoArq(produto.getImagem().getOriginalFilename());
			File arq = new File(nomeArquivo);
			
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(arq));
            stream.write(bytes);
            stream.close();
            
			return arq;

		} catch (Exception e) {
			System.out.println("Erro ao fazer upload de arquivo");
			System.out.println("0 - "+ e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public void removerArqTmp(File arquivo)
	{
		arquivo.delete();
	}
}