package com.middleware.newshop.integraciones.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenModel {
	private String brmId;
	private String accesToken;
	private String refreshToken;

}
