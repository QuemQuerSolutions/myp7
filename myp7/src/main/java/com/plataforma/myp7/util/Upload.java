package com.plataforma.myp7.util;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import com.plataforma.myp7.data.Produto;
import com.plataforma.myp7.enums.ConfigEnum;

public class Upload {

	/**
	 *  Efetua o upload da máquina do usuário para o servidor
	 * @param request Usado para pegar o contexto e o caminho da aplicação
	 * @param file Arquivo a ser efeutado o upload
	 * @return
	 */
	public File armazenar(HttpSession session, Produto produto) {
		
		try {
			byte[] bytes = produto.getImagem().getBytes();
			
			String dataHoraAtual = Utils.getDataAtualString("yyyyMMddHHmmss");
			
			//verifica se não teve nenhum input de imagem
			if(produto.getImagem().getOriginalFilename().equals("")) 
				return new File("");
			
			String dir = session.getServletContext().getRealPath(ConfigEnum.FOLDER_UPLOAD_DEFAULT.getValor());
			Utils.verificaPastaExistente(dir);
			
			String nomeArquivo = dir + File.separator + produto.getIdProduto() + "_" + dataHoraAtual + Utils.getExtensaoArq(produto.getImagem().getOriginalFilename());
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