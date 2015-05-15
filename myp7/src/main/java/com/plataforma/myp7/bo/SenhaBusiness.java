package com.plataforma.myp7.bo;

import java.util.ArrayList;
import java.util.List;

import com.plataforma.myp7.data.HistoricoSenha;
import com.plataforma.myp7.data.Parametro;

public class SenhaBusiness {
	
	public Boolean valida(String senha){
		Boolean validacaoNumeros 		= false;
		Boolean validacaoLetras 		= false;
		Boolean validacaoLetraMaiuscula = false;
		Boolean validacaoQtdeMinima 	= false;
		Boolean validacaoQtdeRepeticao 	= false;
		
		//Substituir o "new ArrayList<Parametro>()" pela chamada do metodo que faz o select
		List<Parametro> parametros = new ArrayList<Parametro>(); 
		
		//MOCK
		Parametro param1 = new Parametro();
		Parametro param2 = new Parametro();
		Parametro param3 = new Parametro();
		Parametro param4 = new Parametro();
		Parametro param5 = new Parametro();
		param1.setNome("NUMEROS");
		param2.setNome("LETRAS");
		param3.setNome("UMA LETRA MAIUSCULA");
		param4.setNome("QTDE MINIMA");
		param4.setAuxiliar("3");
		param5.setNome("QTDE REPETICAO");
		param5.setAuxiliar("1");
		parametros.add(param1);
		parametros.add(param2);
		parametros.add(param3);
		parametros.add(param4);
		parametros.add(param5);
		
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
						validacaoQtdeRepeticao = true;
						break;
					}
					break;
					
				/**
				 * Valida se a quantidade de senhas anteriores igual à da especificado no parametro não repete a senha
				 * que esta sendo informada
				 */
				case "QTDE REPETICAO":
					//Substituir o "new ArrayList<Parametro>()" pela chamada do metodo que faz o select
					//Mandar a quantidade de senhas anteriores que não poderam se repetir como parametro no metodo
					//Integer.parseInt(p.getAuxiliar())
					List<HistoricoSenha> senhasAnteriores = new ArrayList<HistoricoSenha>();
					
					//MOCK
					HistoricoSenha hs = new HistoricoSenha();
					hs.setSenha("a1B");
					senhasAnteriores.add(hs);
					
					for(HistoricoSenha senhaAnterior : senhasAnteriores){
						if(senhaAnterior.getSenha().equals(senha)){
							break;
						}
					}
					validacaoQtdeRepeticao = true;
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
