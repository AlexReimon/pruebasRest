package com.middleware.newshop.integraciones.uitils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceGenericBundle {
	public String getProperties(String sproperties) {
		ResourceBundle bundle = null;
		try {
			bundle = ResourceBundle.getBundle(Constantes.PROPERTIES.getText(),Locale.getDefault());
		} catch (MissingResourceException e) {
			bundle = null;
		}
		
		String cadena = "";
		if(bundle != null) {
			try {
				cadena = bundle.getString(sproperties);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cadena;
		}
		return null;
	}

}
