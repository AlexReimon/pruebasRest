package com.middleware.newshop.integraciones.uitils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class GetConfigPropeties {
	
	@Autowired
	private Environment env;

	public String getConfigValue(String configkey) {
		return env.getProperty(configkey);
	}
	
	
}
