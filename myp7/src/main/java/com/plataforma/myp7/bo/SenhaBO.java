package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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
	
	private final static Logger log = Logger.getLogger(SenhaBO.class);
			
	@Autowired
	private HistoricoSenhaMapper historicoSenhaMapper;
	
	@Autowired
	private ParametroMapper parametroMapper;
	/**
	 * 
	 * @param senha - Senha digitada
	 * @param usuario - Usuario digitado
	 * @param dominio - Perfil do usuario que esta fazendo login
	 * @return Boolean
	 * Metodo para validar se a senha esta de acordo com os parametros.
	 */
	public boolean isValid(String senha, Usuario usuario, ParametroDominio dominio){
		try {
			List<Parametro> parametros;
			parametros = parametroMapper.obterParametro(dominio.getId());
			parametros = new ArrayList<Parametro>();
			
			boolean retorno = true;
			for(Parametro parametro : parametros){
				retorno = retorno && this.casesValidacao(senha, usuario, parametro);
				if(!retorno)
					break;
			}
			return retorno;
		} catch (Exception e) {
			log.error("SenhaBO.isValid", e);
			return false;
		}
	}
	
	private boolean casesValidacao(String senha, Usuario usuario, Parametro parametro){
		try {
			switch(parametro.getNome().toUpperCase()){
				/**
				 * Valida se ha ao menos um numero na senha
				 */			
				case "NUMEROS":
					for(char letra : senha.toCharArray()){
						if(Character.isDigit(letra)){ 
							return true;
						}
					}
					
					return false;
					
				/**
				 * Valida se ha ao menos uma letra na senha
				 */
				case "LETRAS":
					for(char letra : senha.toCharArray()){
						if(Character.isLetter(letra)){ 
							return true;
						}
					}
					return false;
				
				/**
				 * Valida se ha ao menos uma letra maiscula na senha
				 */
				case "UMA LETRA MAIUSCULA":
					for(char letra : senha.toCharArray()){
						if(Character.isUpperCase(letra)){
							return true;
						}
					}
					return false;
					
				/**
				 * Valida se a senha tem a quantidade minima de caracteres requeridos
				 */
				case "QTDE MINIMA":
					return (!(senha.length() < Integer.parseInt(parametro.getAuxiliar())));
			}
			return true;
		} catch (Exception e) {
			log.error("SenhaBO.casesValidacao", e);
			return false;
		}
	}
	
	/**
	 * Valida se a quantidade de senhas anteriores igual a da especificado no parametro nao repete a senha
	 * que esta sendo informada
	 * @param usuario 
	 * @param parametro 
	 */
	public boolean isRepeat(String senha, Usuario usuario, Parametro parametro){
		try {
			String senhaHash = CriptografarBO.criptografar(senha);
			
			List<HistoricoSenha> senhasAnteriores = this.historicoSenhaMapper.obterPorUsuarioELimit(usuario.getIdUsuario(), Integer.parseInt(parametro.getAuxiliar()));
			
			for(HistoricoSenha senhaAnterior : senhasAnteriores){
				if(senhaHash.equals(senhaAnterior.getSenha())){
					return false;
				}
			}
			return true;
		} catch (NumberFormatException e) {
			log.error("SenhaBO.isRepeat", e);
			return false;
		}
	}
	
}
