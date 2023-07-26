package com.middleware.newshop.integraciones.serviceImpl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.google.gson.Gson;
import com.middleware.newshop.integraciones.Vo.EncryModel;
import com.middleware.newshop.integraciones.Vo.EntranceLoginModel;
import com.middleware.newshop.integraciones.Vo.RespLoginModel;
import com.middleware.newshop.integraciones.controller.LoginController;
import com.middleware.newshop.integraciones.service.AccesTokenI;
import com.middleware.newshop.integraciones.uitils.GetConfigPropeties;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Repository
public class AccesTokenImpl implements AccesTokenI{
	
	@Autowired
	private GetConfigPropeties prop;
	
	private static final Logger log = LogManager.getLogger(LoginController.class);
	
	
	@Override
	public  RespLoginModel getToken(EntranceLoginModel elm) throws IOException  {
		log.info("elm :: " +elm.getBrm_id());
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		String endPoint = prop.getConfigValue("url.acces.token") ;
		
		
		
		RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
					  .addFormDataPart("type","credentials")
					  .addFormDataPart("brm_id", elm.getBrm_id().toString())
					  .build();
		Request request = new Request.Builder()
					  .url(endPoint)
					  .method("POST", body)
					  .addHeader("Cookie", "BrowserId=MhDZJi1yEe2tsuHc1kTHsg; __cq_dnt=1; dw_dnt=1; dwanonymous_e57aa79973993dd946dcc634e5f52a32=abFDdnCnGv9ysGolDY2P8GWtk8; dwsid=-IXWhJTOUIX7c6aw2WqHevOdLtxjETa-jTuu9Oro91PuipxykduTWw_S1Uf9W0-Pgv-A248uNa8wp8A6l12xDA==; sid=LldYG9PlDQN44iLYisTOwiVPoEMkOms4Dqo")
					  .build();
		Response response = client.newCall(request).execute();
		String resp = response.body().string();
			
		log.info(resp);
		RespLoginModel rlm =  new Gson().fromJson(resp, RespLoginModel.class);
		return rlm;
	}
}


