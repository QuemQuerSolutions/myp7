package com.plataforma.myp7.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.Representante;
import com.plataforma.myp7.mapper.RepresentanteMapper;

@Service
public class RepresentanteBO {
	
	@Autowired
	private RepresentanteMapper representanteMapper;
	
	public List<Representante> selecionaTodos(){
		return this.representanteMapper.obterTodosRepresentantes();
	}
}
