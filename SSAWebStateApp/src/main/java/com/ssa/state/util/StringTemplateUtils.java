package com.ssa.state.util;

import java.util.Map;

import org.apache.commons.text.StringSubstitutor;


public class StringTemplateUtils {
	public static String prepareDocFromTemplate(String templateContent, Map<String, String> templateValues) {
		StringSubstitutor sub = new StringSubstitutor(templateValues);
		return sub.replace(templateContent);
	}
}
