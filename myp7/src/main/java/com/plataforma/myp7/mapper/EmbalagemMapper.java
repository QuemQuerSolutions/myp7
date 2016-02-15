package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Embalagem;

@Component
public interface EmbalagemMapper {
	
	List<Embalagem> obterTodasEmbalagens(); 
	
	List<Embalagem> obterEmbalagens(Embalagem embalagem);
	
	int countEmbalagem(final Embalagem embalagem);
	
	void salvarEmbalagem(Embalagem embalagem);
	
	void atualizarEmbalagem(Embalagem embalagem);
	
	Embalagem obterEmbalagemPorId(Long id);

	Embalagem obterEmbalagemPorSigla(String siglaEmbalagem);
}

