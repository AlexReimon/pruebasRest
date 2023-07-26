package com.middleware.newshop.integraciones.service;

import com.middleware.newshop.integraciones.Vo.EntranceLoginModel;
import com.middleware.newshop.integraciones.Vo.RespLoginModel;
import com.middleware.newshop.integraciones.Vo.RespnceModel;
import com.middleware.newshop.integraciones.percistence.ModelEntity;

public interface RefreshTokenI {
	public RespnceModel getExistenciaToken(EntranceLoginModel BrmId);
	public boolean calculate(ModelEntity model, EntranceLoginModel BrmId);
	public boolean getUpdate(EntranceLoginModel BrmId, String date_update);
	public RespnceModel createResponce(EntranceLoginModel elm, RespLoginModel rlm);
}
