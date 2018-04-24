package com.xx.leave.utils.commom;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;


public class DateConverter implements Converter {

	@SuppressWarnings("rawtypes")
	public Object convert(Class type, Object value) {
		if(value == null) return null;
		if(!(value instanceof String)) {
			return value;
		}
		String val = (String) value;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(val);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
