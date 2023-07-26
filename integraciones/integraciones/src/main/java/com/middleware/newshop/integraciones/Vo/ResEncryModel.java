package com.middleware.newshop.integraciones.Vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResEncryModel {
	private Result result;
	private List<Dato> datos;
	private String idAccessReturn;
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Result{
		private String result;
		private String resultDescription;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Dato{
		private String dato;
	}

}
