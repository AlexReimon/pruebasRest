package com.middleware.newshop.integraciones.Vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EncryModel {
	private String idAccess;
	private List<Dato> datos;
	private int opc;
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Dato{
		private String dato;
	}

}
