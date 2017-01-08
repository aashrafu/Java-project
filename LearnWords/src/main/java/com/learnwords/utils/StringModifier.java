package com.learnwords.utils;

public class StringModifier {

	public static String removeSymbols(String string)
	{
		string = string.replaceAll("\\,", "").replaceAll("\\.", "").replaceAll("\\:", "")
				.replaceAll("\\;", "").replaceAll("\\!", "").replaceAll("\"", "").replaceAll("\\?", "")
				.replaceAll("\'", "").replaceAll("\\-", "").trim();
		
		return string;
	}
}
