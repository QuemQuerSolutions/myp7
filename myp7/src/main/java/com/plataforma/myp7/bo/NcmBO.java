package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.setCodRetorno;
import static com.plataforma.myp7.util.Utils.setMsgRetorno;

import org.springframework.ui.Model;

import com.plataforma.myp7.dao.NcmDAO;
import com.plataforma.myp7.data.NCM;

public class NcmBO {

	public boolean validaNcm(NCM ncmProduto, Model model) {
		if(new NcmDAO().selecionaPorCodigo(ncmProduto) == null){
			setMsgRetorno(model, "NCM não encontrado.");
			setCodRetorno(model, 1);
			return false;
		}
		return true;
	}

}
