package com.plataforma.myp7.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	private static final String FMT_DATA_DEFAULT = "dd/MM/yyyy";
	
	public static String getDataAtualString(String formato) {
		return new SimpleDateFormat(formato).format(new Date());
	}
	
	public static String getDataString(Date date) {
		return new SimpleDateFormat(FMT_DATA_DEFAULT).format(date);
	}
}
