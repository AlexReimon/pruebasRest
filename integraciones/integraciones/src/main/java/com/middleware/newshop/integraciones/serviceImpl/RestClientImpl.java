package com.middleware.newshop.integraciones.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.middleware.newshop.integraciones.Vo.EncryModel;
import com.middleware.newshop.integraciones.Vo.EncryModel.Dato;
import com.middleware.newshop.integraciones.Vo.EntranceLoginModel;
import com.middleware.newshop.integraciones.Vo.ResEncryModel;
import com.middleware.newshop.integraciones.percistence.RepocitoryMCS;
import com.middleware.newshop.integraciones.service.RestClientI;
import com.middleware.newshop.integraciones.uitils.Constantes;
import com.middleware.newshop.integraciones.uitils.GetConfigPropeties;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


@Service
public class RestClientImpl implements RestClientI{
	
	private static final Logger log = LogManager.getLogger(RestClientImpl.class);
	@Autowired
	private RepocitoryMCS queryCS;
	
	@Autowired
	private GetConfigPropeties prop;

	@Override
	public ResEncryModel  getResponceClient(EntranceLoginModel elm, String fromData, final int flag) throws Exception{
		log.info("fromData :: " + fromData.toString());
		log.info("ENDPOINT___:::: " + Constantes.SERVICE_ACCS_TOKEN.getText());
		int timeOut = 60;
		
//		String endPoint = "https://apiservice-dev.sistemastp.com.mx/v1/mdl-tools/rsaEncript";
		String endPoint = prop.getConfigValue("url.encrypt") ;
		
		EncryModel cuerpo = new EncryModel();
		Dato recordEncry  = new Dato(fromData);
		List<Dato> datos = new ArrayList<Dato>();
		
		datos.add(recordEncry);
		cuerpo.setIdAccess(elm.getIdAcces());
		cuerpo.setDatos(datos);
		cuerpo.setOpc(flag);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(cuerpo);
		log.info(jsonStr);
		try {
			OkHttpClient client = new OkHttpClient();
			
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody bodyReq = RequestBody.create(mediaType, jsonStr);
			Request requestR = new Request.Builder().url(endPoint).post(bodyReq)
					.addHeader("Content-Type", "application/json").addHeader("Accept", "application/json").build();
			Response response = client.newCall(requestR).execute();
			log.info("/response params ::: " + response.toString());
			String result = response.body().string();
			
			log.info("/consumeApimeRest ::: response =" + result);
			ResEncryModel rlm =  (ResEncryModel) new Gson().fromJson(result, ResEncryModel.class);
			return rlm;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
