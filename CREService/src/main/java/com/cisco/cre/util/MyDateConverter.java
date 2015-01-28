package com.cisco.cre.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

public class MyDateConverter extends StdConverter<String, Date> {
	
	@Override
	public Date convert(final String value) {
		if (value == null || value.equals("") || value.equals("NULL")) {
			return null;
		}
		
		try {
			Date date = new SimpleDateFormat("dd-MMM-yy").parse(value);
			return date;
		} catch (java.text.ParseException e) {					
			throw new IllegalStateException("Unable to parse date: "+value, e);
		}
		
	}

}
