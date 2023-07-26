package com.middleware.newshop.integraciones.uitils;

public enum Constantes {
	PROPERTIES(1, "main.resources.application"),
	SERVICE_ACCS_TOKEN(2, getProperties("serv.acces.token"));
	
	
	private final int key;
	private final String text;
	
	private Constantes(int key, String text) {
		this.key = key;
		this.text = text;
	}
	public int getKey() {
		return key;
	}
	public String getText() {
		return text;
	}
	
	public static String getProperties(String properti) {
		return new ResourceGenericBundle().getProperties(properti);
	}



}
