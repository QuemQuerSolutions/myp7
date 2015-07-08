package com.plataforma.myp7.bo;

import java.util.List;

import com.plataforma.myp7.dao.RepresentanteDAO;
import com.plataforma.myp7.data.Representante;

public class RepresentanteBO {
	private RepresentanteDAO representanteDAO;
	
	public RepresentanteBO(){
		this.representanteDAO = new RepresentanteDAO();
	}
	
	public List<Representante> selecionaTodos(){
		return this.representanteDAO.selecionaTodos();
	}
}
