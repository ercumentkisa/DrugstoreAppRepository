package com.drugstore.client.util;

import java.io.Serializable;

public final class StringUtil implements Serializable{
	
	/**
	 * Escape an html string to
	 * prevent cross-site script vulnerabilities.
	 */
	public static String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
	
	public static boolean isEmpty(String string){
		return ((string == null) || string.equals(""));
	}
	
	public static boolean isNotEmpty(String string){
		return !isEmpty(string);
	}
}
