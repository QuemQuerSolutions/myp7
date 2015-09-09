package com.plataforma.myp7.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.plataforma.myp7.data.Comprador;

@Component
public interface CompradorMapper {
	int count(Comprador comprador);
	Comprador obterPorId(Integer id);
	List<Comprador> obterPorParametro(Comprador comprador);
}
