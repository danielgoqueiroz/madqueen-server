package com.danielqueiroz.madqueenserver.utils;

public class Validation {

	public static boolean validate(String value, int sizeLimitMinimun, int sizeLimitMaximun) {
		if (value == null) {
			return false;
		}
		if (value.isEmpty()) {
			return false;
		}
		if (value.length() < sizeLimitMinimun) {
			return false;
		}
		if (value.length() > sizeLimitMaximun) {
			return false;
		}
		return true;
	}

}