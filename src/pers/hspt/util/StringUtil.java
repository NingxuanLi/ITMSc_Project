package pers.hspt.util;

import java.util.regex.Pattern;

public class StringUtil {
	
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	
	public static void main(String[] args) {
		String str = "123";
		System.out.println(isInteger(str));
	}

}
