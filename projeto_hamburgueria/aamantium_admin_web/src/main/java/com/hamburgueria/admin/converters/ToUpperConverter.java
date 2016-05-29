package com.hamburgueria.admin.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="toUpperConverter")
public class ToUpperConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return value.toUpperCase();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value instanceof String){
			String stringValue = (String) value;
			return stringValue.toUpperCase();
		}
		return null;
	}

}
