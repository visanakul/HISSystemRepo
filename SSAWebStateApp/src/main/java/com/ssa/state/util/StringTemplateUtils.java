package com.ssa.state.util;

import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

public class StringTemplateUtils {
	public static String prepareDocFromTemplate(String templateContent, Map<String, String> templateValues) {
		StrSubstitutor sub = new StrSubstitutor(templateValues);
		String result = sub.replace(templateContent);
		return result;
	}
}
