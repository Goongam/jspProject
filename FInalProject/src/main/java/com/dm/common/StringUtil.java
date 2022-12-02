package com.dm.common;

public class StringUtil {
	public static String getURLEscapeCode(String s) {
		return s.replace("&","%26").replace("+","%2B").replace("#","%23");
	}

}
