package com.plataforma.myp7.mapper;

import java.util.List;

//import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.NCM;


//@Component
public interface NCMMapper {

	public NCM obterPorId(Long id);
	
	public List<NCM> obterTodos();
}
