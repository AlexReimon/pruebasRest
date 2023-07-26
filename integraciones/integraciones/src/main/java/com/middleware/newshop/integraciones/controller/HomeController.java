package com.middleware.newshop.integraciones.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


@RestController
public class HomeController {

	@GetMapping("/getToken")
	public String getToken() throws IOException {

		OkHttpClient client = new OkHttpClient().newBuilder().build();
//				MediaType mediaType = (MediaType) MediaType.parseMediaTypes("text/plain");
				RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
				  .addFormDataPart("type","credentials")
				  .addFormDataPart("brm_id","0100677433")
				  .build();
				Request request = new Request.Builder()
				  .url("https://bjcl-003.dx.commercecloud.salesforce.com/on/demandware.store/Sites-TP-FOOD-Site/es/SLASUtils-InterfaceLogin")
				  .method("POST", body)
				  .addHeader("Cookie", "BrowserId=MhDZJi1yEe2tsuHc1kTHsg; __cq_dnt=1; dw_dnt=1; dwanonymous_e57aa79973993dd946dcc634e5f52a32=abFDdnCnGv9ysGolDY2P8GWtk8; dwsid=-IXWhJTOUIX7c6aw2WqHevOdLtxjETa-jTuu9Oro91PuipxykduTWw_S1Uf9W0-Pgv-A248uNa8wp8A6l12xDA==; sid=LldYG9PlDQN44iLYisTOwiVPoEMkOms4Dqo")
				  .build();
		Response response = client.newCall(request).execute();
		String resp = response.body().string();
		return resp;
	}
	
	
	
}
