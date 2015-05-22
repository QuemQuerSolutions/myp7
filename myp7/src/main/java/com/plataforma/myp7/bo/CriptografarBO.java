package com.plataforma.myp7.bo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografarBO  {
	public static String criptografar(String palavra) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
		byte criptografia[] = algoritmo.digest(palavra.getBytes("UTF-8")); 
		
		StringBuilder hexHash = new StringBuilder();
		for(byte b : criptografia) {
		  hexHash.append(String.format("%02X", 0xFF & b));
		}
		
		return hexHash.toString();
	}
}
