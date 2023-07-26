package com.middleware.newshop.integraciones.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.middleware.newshop.integraciones.Vo.EntranceLoginModel;
import com.middleware.newshop.integraciones.Vo.RespLoginModel;
import com.middleware.newshop.integraciones.Vo.RespnceModel;
import com.middleware.newshop.integraciones.serviceImpl.RefreshTokenImp;



@RestController
public class LoginController {

	private static final Logger log = LogManager.getLogger(LoginController.class);

	@Autowired
	private RefreshTokenImp refreshToken;

	@PostMapping("/integraciones/geInfoToken")
	public @ResponseBody RespnceModel loginInterface(@RequestBody EntranceLoginModel elm) {
		log.info("EntranceLoginModel :: " + elm);
		RespnceModel exist = refreshToken.getExistenciaToken(elm);
		return exist;
	}
}
