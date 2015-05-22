package com.plataforma.myp7.bo;

import java.util.List;

import com.plataforma.myp7.dao.HistoricoSenhaDAO;
import com.plataforma.myp7.dao.ParametroDAO;
import com.plataforma.myp7.data.HistoricoSenha;
import com.plataforma.myp7.data.Parametro;
import com.plataforma.myp7.data.ParametroDominio;
import com.plataforma.myp7.data.Usuario;

public class SenhaBO {
	
	/**
	 * 
	 * @param senha - Senha digitada
	 * @param usuario - Usuario digitado
	 * @param dominio - Perfil do usuário que esta fazendo login
	 * @return Boolean
	 * Método para validar se a senha esta de acordo com os parametros.
	 */
	public boolean isValido(String senha, Usuario usuario, ParametroDominio dominio){
		ParametroDAO parametroDAO = new ParametroDAO();
		List<Parametro> parametros = parametroDAO.selecionarPorDominio(dominio);
		
		boolean retorno = true;
		for(Parametro parametro : parametros){
			retorno = retorno && this.casesValidacao(senha, usuario, parametro);
			if(!retorno)
				break;
		}
		return retorno;
	}
	
	private boolean casesValidacao(String senha, Usuario usuario, Parametro parametro){
		HistoricoSenhaDAO historicoSenhaDAO = new HistoricoSenhaDAO();
		
		switch(parametro.getNome().toUpperCase()){
			/**
			 * Valida se há ao menos um numero na senha
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
			 * Valida se há ao menos uma letra maúscula na senha
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
				if(senha.length() < Integer.parseInt(parametro.getAuxiliar())){ 
					return false;
				}
				return true;
				
			/**
			 * Valida se a quantidade de senhas anteriores igual à da especificado no parametro não repete a senha
			 * que esta sendo informada
			 */
			case "QTDE REPETICAO":
				try{
					String senhaHash = CriptografarBO.criptografar(senha);
					
					List<HistoricoSenha> senhasAnteriores = historicoSenhaDAO.selecionarPorUsuario(usuario, Integer.parseInt(parametro.getAuxiliar()));
					
					for(HistoricoSenha senhaAnterior : senhasAnteriores){
						if(senhaHash.equals(senhaAnterior.getSenha())){
							throw new Exception();
						}
					}
				}catch(Exception e){
					return false;
				}
				return true;
		}
		return true;
	}
}
