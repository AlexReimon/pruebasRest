package com.middleware.newshop.integraciones.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespLoginModel {
	private String action;
	private String queryString;
	private String locale;
	private String proxiedApi;
	private OcapiResponse ocapiResponse;
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class OcapiResponse{
		private String access_token;
		private String refresh_token;
		private String customer_id;
		private String customer_email;
		private String customer_name;
		private String c_brmId;
		private String c_basketId;
		
	}
	


	

}
