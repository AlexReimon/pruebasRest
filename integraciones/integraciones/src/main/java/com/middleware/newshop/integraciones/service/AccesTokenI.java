package com.middleware.newshop.integraciones.service;

import java.io.IOException;

import com.middleware.newshop.integraciones.Vo.EntranceLoginModel;
import com.middleware.newshop.integraciones.Vo.RespLoginModel;

public interface AccesTokenI {
	public RespLoginModel getToken(EntranceLoginModel elm) throws IOException;

}
