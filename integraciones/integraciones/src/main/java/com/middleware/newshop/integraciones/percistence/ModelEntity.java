package com.middleware.newshop.integraciones.percistence;

import javax.persistence.*;

import lombok.Data;

@Entity(name="MKT_LOANS_RECORD")
@Data
public class ModelEntity{
	@Id
	@Column(name="CUENTA_BRM")
	String brm_id;
	
	@Column(name="LOANS_CUPOUTILIZADO")
	String acces_token;

	@Column(name="LOANS_LINEACREDITO")
	String refresh_token;
	
	@Column(name="LOANS_CUPOASIGNADO")
	String responce;
	
	@Column(name="LOANS_DIASMORA")
	String customer_id;
	
	@Column(name="LOANS_TIPOMENSAJE")
	String basket_id;
	
	@Column(name="LAST_UPDATE")
	String last_update_t;
	
	
	

}
