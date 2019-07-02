package com.ssa.util;

public class SSNUtil {
	public static String getSSNFormat(Integer ssn) {
		return new StringBuilder(ssn.toString()).insert(3, '-').insert(7, '-').toString();
	}
}
