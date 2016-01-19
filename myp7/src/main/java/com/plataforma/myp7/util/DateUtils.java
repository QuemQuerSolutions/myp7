package com.plataforma.myp7.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtils {
	private final static Logger log = Logger.getLogger(DateUtils.class);
	private static final String FMT_DATA_DEFAULT = "dd/MM/yyyy";
	
	public static String getDataAtualString(String formato) {
		return new SimpleDateFormat(formato).format(new Date());
	}
	
	public static String getDataString(Date date) {
		return new SimpleDateFormat(FMT_DATA_DEFAULT).format(date);
	}
	
	public static Date getDate(String data, String formato){
		if(data == null || data == "") return null;
		
        DateFormat formatter = new SimpleDateFormat(formato);  
        try {
        	return formatter.parse(data);
		} catch (Exception e) {
			log.error(StringUtils.concat("DateUtils.getDate- data:", data, " formato:", formato), e);
			return null;
		}  
	}
}
