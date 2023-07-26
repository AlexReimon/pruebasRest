package com.middleware.newshop.integraciones.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespnceModel {

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
			private Result customer_id;
			private String customer_email;
			private String customer_name;
			private Result c_brmId;
			private String c_basketId;
			
		}
		
		@AllArgsConstructor
		@NoArgsConstructor
		@Data
		public static class Result{
			private String dato;
			private String idAccessReturn;
		}

}
