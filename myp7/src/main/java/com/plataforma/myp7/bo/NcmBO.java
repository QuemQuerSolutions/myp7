package com.plataforma.myp7.bo;

import com.plataforma.myp7.dao.NcmDAO;
import com.plataforma.myp7.data.NCM;

public class NcmBO {

	public boolean validaNcm(NCM ncmProduto) {
		return new NcmDAO().selecionaPorCodigo(ncmProduto) != null;
	}

}
