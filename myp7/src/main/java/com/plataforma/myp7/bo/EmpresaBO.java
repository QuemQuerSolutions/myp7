package com.plataforma.myp7.bo;

import java.util.List;

import com.plataforma.myp7.dao.EmpresaDAO;
import com.plataforma.myp7.data.Empresa;

public class EmpresaBO {
	private EmpresaDAO empresaDAO;
	
	public EmpresaBO(){
		this.empresaDAO = new EmpresaDAO();
	}
	
	public List<Empresa> selecionaTodos(){
		return this.empresaDAO.selecionaTodos();
	}
}
