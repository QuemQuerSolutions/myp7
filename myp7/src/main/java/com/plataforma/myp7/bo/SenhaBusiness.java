package com.plataforma.myp7.bo;

import java.util.List;

import com.plataforma.myp7.DAO.HistoricoSenhaDAO;
import com.plataforma.myp7.DAO.ParametroDAO;
import com.plataforma.myp7.data.DominioParametro;
import com.plataforma.myp7.data.HistoricoSenha;
import com.plataforma.myp7.data.Parametro;
import com.plataforma.myp7.data.Usuario;

public class SenhaBusiness {
	
	/**
	 * 
	 * @param senha - Senha digitada
	 * @param usuario - Usuario digitado
	 * @param dominio - Perfil do usuário que esta fazendo login
	 * @return Boolean
	 * Método para validar se a senha esta de acordo com os parametros.
	 */
	public Boolean valida(String senha, Usuario usuario, DominioParametro dominio){
		ParametroDAO parametroDAO = new ParametroDAO();
		HistoricoSenhaDAO historicoSenhaDAO = new HistoricoSenhaDAO();
		
		Boolean validacaoNumeros 		= false;
		Boolean validacaoLetras 		= false;
		Boolean validacaoLetraMaiuscula = false;
		Boolean validacaoQtdeMinima 	= false;
		Boolean validacaoQtdeRepeticao 	= false;
		
//		List<Parametro> parametros = new ArrayList<Parametro>(); 
		List<Parametro> parametros = parametroDAO.selecionarPorDominio(dominio);
		
		//MOCK
//		Parametro param1 = new Parametro();
//		Parametro param2 = new Parametro();
//		Parametro param3 = new Parametro();
//		Parametro param4 = new Parametro();
//		Parametro param5 = new Parametro();
//		param1.setNome("NUMEROS");
//		param2.setNome("LETRAS");
//		param3.setNome("UMA LETRA MAIUSCULA");
//		param4.setNome("QTDE MINIMA");
//		param4.setAuxiliar("5");
//		param5.setNome("QTDE REPETICAO");
//		param5.setAuxiliar("1");
//		parametros.add(param1);
//		parametros.add(param2);
//		parametros.add(param3);
//		parametros.add(param4);
//		parametros.add(param5);
		
		for(Parametro p : parametros){
			switch(p.getNome().toUpperCase()){
				/**
				 * Valida se há ao menos um número na senha
				 */			
				case "NUMEROS":
					for(char letra : senha.toCharArray()){
						if(Character.isLetter(letra)){
							validacaoNumeros = true;
							break;
						}
					}
					break;
					
				/**
				 * Valida se há ao menos uma letra na senha
				 */
				case "LETRAS":
					for(char letra : senha.toCharArray()){
						if(Character.isDigit(letra)){
							validacaoLetras = true;
							break;
						}
					}
					break;
				
				/**
				 * Valida se há ao menos uma letra maúscula na senha
				 */
				case "UMA LETRA MAIUSCULA":
					for(char letra : senha.toCharArray()){
						if(Character.isUpperCase(letra)){
							validacaoLetraMaiuscula = true;
							break;
						}
					}
					break;
					
				/**
				 * Valida se a senha tem a quantidade mínima de caracteres requeridos
				 */
				case "QTDE MINIMA":
					if(senha.toCharArray().length >= Integer.parseInt(p.getAuxiliar())){
						validacaoQtdeMinima = true;
						break;
					}
					break;
					
				/**
				 * Valida se a quantidade de senhas anteriores igual à da especificado no parametro não repete a senha
				 * que esta sendo informada
				 */
				case "QTDE REPETICAO":
//					List<HistoricoSenha> senhasAnteriores = new ArrayList<HistoricoSenha>();
					List<HistoricoSenha> senhasAnteriores = historicoSenhaDAO.selecionarPorUsuario(usuario, Integer.parseInt(p.getAuxiliar()));
					
					//MOCK
//					HistoricoSenha hs = new HistoricoSenha();
//					hs.setSenha("a1A");
//					senhasAnteriores.add(hs);
					
					boolean achouIgual = false;
					for(HistoricoSenha senhaAnterior : senhasAnteriores){
						if(senhaAnterior.getSenha().equals(senha)){
							achouIgual = true;
							break;
						}
					}
					validacaoQtdeRepeticao = !achouIgual;
					break;
			}
		}
		
		return validacaoNumeros 
				&& validacaoLetras 
				&& validacaoLetraMaiuscula 
				&& validacaoQtdeMinima 
				&& validacaoQtdeRepeticao;
	}
}
