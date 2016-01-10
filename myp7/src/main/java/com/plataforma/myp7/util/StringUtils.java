package com.plataforma.myp7.util;

public class StringUtils {
	
	/**
	 * Concatenador de Strings
	 * @param args
	 * @return
	 */
	public static String concat(Object ... args){
		StringBuilder s = new StringBuilder();
		for(int i=0, len=args.length; i < len ; i++){
			s.append(args[i]);
		}
		return s.toString();
	}
	
}
