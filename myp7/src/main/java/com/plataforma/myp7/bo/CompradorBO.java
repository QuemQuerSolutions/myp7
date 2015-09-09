package com.plataforma.myp7.bo;

import static com.plataforma.myp7.util.Utils.setRetorno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plataforma.myp7.data.Comprador;
import com.plataforma.myp7.enums.ConfigEnum;
import com.plataforma.myp7.enums.Mensagem;
import com.plataforma.myp7.mapper.CompradorMapper;

@Service
public class CompradorBO {
	
	@Autowired
	private CompradorMapper compradorMapper;
	
	public Comprador obterPorId(Integer id){
		return compradorMapper.obterPorId(id);
	}
	
	public List<Comprador> obterPorParametro(Model model, Comprador comp){
		Comprador comprador = new Comprador(comp);
		
		int count = compradorMapper.count(comprador);
		
		if(count == 0){
			setRetorno(model, Mensagem.NENHUM_REGISTRO_LOCALIZADO);
			return null;
		}
		
		if(count > ConfigEnum.LIMITE_COUNT.getValorInt()){
			setRetorno(model, Mensagem.REFINE_SUA_PESQUISA);
			return null;
		}
		
		return compradorMapper.obterPorParametro(comprador);
	}
}
