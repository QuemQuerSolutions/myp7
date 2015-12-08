package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plataforma.myp7.data.HistoricoSenha;
import com.plataforma.myp7.data.Parametro;
import com.plataforma.myp7.data.ParametroDominio;
import com.plataforma.myp7.data.Usuario;
import com.plataforma.myp7.mapper.HistoricoSenhaMapper;
import com.plataforma.myp7.mapper.ParametroMapper;

@Service
public class SenhaBO {
	
	@Autowired
	private HistoricoSenhaMapper historicoSenhaMapper;
	
	@Autowired
	private ParametroMapper parametroMapper;
	/**
	 * 
	 * @param senha - Senha digitada
	 * @param usuario - Usuario digitado
	 * @param dominio - Perfil do usu�rio que esta fazendo login
	 * @return Boolean
	 * M�todo para validar se a senha esta de acordo com os parametros.
	 */
	public boolean isValid(String senha, Usuario usuario, ParametroDominio dominio){
		List<Parametro> parametros;
		try {
			parametros = parametroMapper.obterParametro(dominio.getId());
		} catch (Exception e) {
			parametros = new ArrayList<Parametro>();
		}
		
		boolean retorno = true;
		for(Parametro parametro : parametros){
			retorno = retorno && this.casesValidacao(senha, usuario, parametro);
			if(!retorno)
				break;
		}
		return retorno;
	}
	
	private boolean casesValidacao(String senha, Usuario usuario, Parametro parametro){
		switch(parametro.getNome().toUpperCase()){
			/**
			 * Valida se h� ao menos um numero na senha
			 */			
			case "NUMEROS":
				for(char letra : senha.toCharArray()){
					if(Character.isDigit(letra)){ 
						return true;
					}
				}
				
				return false;
				
			/**
			 * Valida se há ao menos uma letra na senha
			 */
			case "LETRAS":
				for(char letra : senha.toCharArray()){
					if(Character.isLetter(letra)){ 
						return true;
					}
				}
				return false;
			
			/**
			 * Valida se há ao menos uma letra maiscula na senha
			 */
			case "UMA LETRA MAIUSCULA":
				for(char letra : senha.toCharArray()){
					if(Character.isUpperCase(letra)){
						return true;
					}
				}
				return false;
				
			/**
			 * Valida se a senha tem a quantidade mínima de caracteres requeridos
			 */
			case "QTDE MINIMA":
				return (!(senha.length() < Integer.parseInt(parametro.getAuxiliar())));
		}
		return true;
	}
	
	/**
	 * Valida se a quantidade de senhas anteriores igual a da especificado no parametro não repete a senha
	 * que esta sendo informada
	 * @param usuario 
	 * @param parametro 
	 */
	public boolean isRepeat(String senha, Usuario usuario, Parametro parametro){
		String senhaHash = CriptografarBO.criptografar(senha);
		
		List<HistoricoSenha> senhasAnteriores = this.historicoSenhaMapper.obterPorUsuarioELimit(usuario.getIdUsuario(), Integer.parseInt(parametro.getAuxiliar()));
		
		for(HistoricoSenha senhaAnterior : senhasAnteriores){
			if(senhaHash.equals(senhaAnterior.getSenha())){
				return false;
			}
		}
		return true;
	}
	
}
