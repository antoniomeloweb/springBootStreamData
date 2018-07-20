package com.cartao.utils;

import java.text.ParseException;
import java.util.Date;

public final class Utils {
	public static String padLeft(String str, int n, char chr) {
		return String.format("%1$" + n + "s", str).replace(' ', chr);
	}
	  
	public static Boolean validateDateString(String dateStr) {
		try {
			// se conseguir dar um parse nesta data retorna true
			Date dt = Constants.DATE_FORMAT.parse(dateStr);
			return true;
		} catch (Exception e) {
			try {
				Date dt = Constants.DATE_FORMAT_FULL.parse(dateStr);
				return true;
			} catch (ParseException e1) {
				return false;
			}
		}
	}
}
