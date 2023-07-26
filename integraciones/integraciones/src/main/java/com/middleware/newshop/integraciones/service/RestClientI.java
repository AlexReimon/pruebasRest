package com.middleware.newshop.integraciones.service;

import com.middleware.newshop.integraciones.Vo.EncryModel;
import com.middleware.newshop.integraciones.Vo.EntranceLoginModel;
import com.middleware.newshop.integraciones.Vo.ParamsCallService;
import com.middleware.newshop.integraciones.Vo.ResEncryModel;
import com.middleware.newshop.integraciones.Vo.RespLoginModel;

public interface RestClientI {
	public ResEncryModel getResponceClient(EntranceLoginModel elm, String fromData, int flag) throws Exception; 

}
