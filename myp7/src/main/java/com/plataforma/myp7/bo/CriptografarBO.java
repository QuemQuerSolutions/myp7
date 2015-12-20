package com.plataforma.myp7.bo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class CriptografarBO  {
	public static String criptografar(String palavra) {
		MessageDigest algoritmo = null;
		try {
			algoritmo = MessageDigest.getInstance("MD5");
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, algoritmo.digest(palavra.getBytes()));
		return hash.toString(16);
	}
}
