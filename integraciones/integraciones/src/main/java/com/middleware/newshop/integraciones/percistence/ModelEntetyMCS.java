package com.middleware.newshop.integraciones.percistence;

import javax.persistence.*;

import lombok.Data;

@Entity(name= "MKT_CONFIGURATION_SERVICE")
@Data
public class ModelEntetyMCS {
	@Id
	@Column(name = "NAME")
	String name;
	
	@Column(name = "VALUE")
	String value;
	

}