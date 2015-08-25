package com.plataforma.myp7.bo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class CriptografarBO  {
	public static String criptografar(String palavra) {
		StringBuilder hexHash = new StringBuilder();
		try {
			MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
			byte criptografia[] = algoritmo.digest(palavra.getBytes("UTF-8")); 
			
			for(byte b : criptografia) {
			  hexHash.append(String.format("%02X", 0xFF & b));
			}
			
		}catch(NoSuchAlgorithmException | UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		return hexHash.toString();
	}
}
