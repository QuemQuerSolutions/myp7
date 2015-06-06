package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import org.springframework.ui.Model;

import com.plataforma.myp7.data.Produto;

public class ProdutoBO {

	public Boolean salvar(Produto produto, Model model) {
		if(this.isInsertValido(produto, model)){
			setMsgRetorno(model, "Embalagem salva com sucesso");
			setCodRetorno(model, 0);
			return true;
		}
		return false;
	}

	public Boolean isInsertValido(Produto produto, Model model) {
		return new NcmBO().validaNcm(produto.getNcmProduto(), model);
	}
}
