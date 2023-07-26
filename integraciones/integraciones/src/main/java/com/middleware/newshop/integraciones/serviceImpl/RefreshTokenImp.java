package com.middleware.newshop.integraciones.serviceImpl;

import java.lang.annotation.Annotation;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.middleware.newshop.integraciones.Vo.EncryModel;
import com.middleware.newshop.integraciones.Vo.EntranceLoginModel;
import com.middleware.newshop.integraciones.Vo.ResEncryModel;
import com.middleware.newshop.integraciones.Vo.RespLoginModel;
import com.middleware.newshop.integraciones.Vo.RespnceModel;
import com.middleware.newshop.integraciones.Vo.RespnceModel.Result;
import com.middleware.newshop.integraciones.controller.LoginController;
import com.middleware.newshop.integraciones.percistence.ModelEntity;
import com.middleware.newshop.integraciones.percistence.QueryRepository;
import com.middleware.newshop.integraciones.service.RefreshTokenI;

import kotlin.jvm.Throws;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class RefreshTokenImp implements RefreshTokenI {

	private static final Logger log = LogManager.getLogger(LoginController.class);

	@Autowired
	private AccesTokenImpl restClient;

	@Autowired
	private QueryRepository query;

	@Autowired
	private RestClientImpl resClientEncrypt;

	@Override
	public RespnceModel getExistenciaToken(EntranceLoginModel BrmId) {
		log.info("cbrm ::: " + BrmId.getBrm_id());
		BrmId.setBrm_id(getDesincrypt(BrmId, BrmId.getBrm_id()));
		ModelEntity count = query.SelectExist(BrmId.getBrm_id());
		log.info("model ::: " + count);
		RespnceModel rmResponce = new RespnceModel();
		RespLoginModel ob = null;
		try {
			if (count != null) {
				if (calculate(count, BrmId)) {
					ModelEntity countUpdate = query.SelectExist(BrmId.getBrm_id());
					rmResponce = new Gson().fromJson(countUpdate.getResponce(), RespnceModel.class);
				} else {
					log.info("Esta en tiempo ::: ");
					rmResponce = new Gson().fromJson(count.getResponce(), RespnceModel.class);
				}
			} else {
				ob = restClient.getToken(BrmId);
				rmResponce = createResponce(BrmId, ob);
				if (rmResponce != null) {
					ObjectMapper mapper = new ObjectMapper();
					String jsonStr = mapper.writeValueAsString(rmResponce);

					query.Insert(ob.getOcapiResponse().getC_brmId(), ob.getOcapiResponse().getAccess_token(),
							ob.getOcapiResponse().getRefresh_token(), jsonStr, ob.getOcapiResponse().getCustomer_id(),
							ob.getOcapiResponse().getC_basketId());
				} else {
					log.error("Error/ Fallo al  insertar ");
				
				}
			}
		} catch (Exception e) {
			log.error("Fallo al consumir el servicio ");
			e.printStackTrace();
			rmResponce.setAction("error");
		}

		return rmResponce;
	}

	@Override
	public boolean calculate(ModelEntity model, EntranceLoginModel BrmId) {
		log.info("CALCULATE::INIT::");
//		boolean resp = null;					
		Calendar calendario = new GregorianCalendar();
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		int mes = calendario.get(Calendar.MONTH) + 1;
		int anio = calendario.get(Calendar.YEAR);
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		int minutos = calendario.get(Calendar.MINUTE);
		int segundos = calendario.get(Calendar.SECOND);
		log.info("For " + model.getBrm_id() + "Calculate::" + model.getLast_update_t());

		String mod = model.getLast_update_t().toString();
		String[] parts = mod.split(" ");
		log.info(parts.toString());

		String dateL = parts[0];
		log.info(dateL);
		String[] dateDetail = dateL.split("-");
		Integer year = Integer.parseInt(dateDetail[0]);
		Integer month = Integer.parseInt(dateDetail[1]);
		Integer day = Integer.parseInt(dateDetail[2]);

		String timeL = parts[1];
		timeL = timeL.substring(0, timeL.length() - 2);
		String[] timeDetail = timeL.split(":");
		Integer hour = Integer.parseInt(timeDetail[0]);
		Integer minutes = Integer.parseInt(timeDetail[1]);
		Integer seconds = Integer.parseInt(timeDetail[2]);

		log.info("Year::" + year + " month::" + month + " day::" + day);
		log.info("hour::" + hour + " minutes::" + minutes + " seconds::" + seconds);
		log.info("Hour Now::" + hora + " minutes::" + minutos + " seconds::" + segundos);

		String DteUpte = dia + "/" + mes + "/" + anio + " " + hora + ":" + minutos + ":" + segundos;
		log.info("Format date_update :: " + DteUpte);

		Integer minu = 60;
		Boolean upteToken = false;
		if (month == mes && day == dia) {
			if (hour == hora) {
				if (minutos - minutes >= 20) {
					log.info("Proccess time");
					upteToken = getUpdate(BrmId, DteUpte);

					if (upteToken) {
						log.info("update por minutos");
					} else {
						log.info("Error/ fallo el update ");
					}

				} else {
					upteToken = false;
				}
			} else {
				if (((minu - minutes) + minutos) >= 20) {
					upteToken = getUpdate(BrmId, DteUpte);
					log.info("update por hora y minuto");
				} else {
					upteToken = false;
				}
			}
		} else {
			upteToken = getUpdate(BrmId, DteUpte);
			log.info("update por dia o mes");
		}
		return upteToken;
	}

	@Override
	public boolean getUpdate(EntranceLoginModel BrmId, String date_update) {
		log.info("getUpdate :: " + BrmId.getBrm_id());
		RespLoginModel ob = null;
		RespnceModel rmResponce = null;
		try {
			ob = restClient.getToken(BrmId);
			log.info(":::::::  " + ob.toString());
			rmResponce = createResponce(BrmId, ob);
			if (rmResponce != null) {
				ObjectMapper mapper = new ObjectMapper();
				String jsonStr = mapper.writeValueAsString(ob);
				query.updateToken(ob.getOcapiResponse().getAccess_token(), ob.getOcapiResponse().getRefresh_token(),
						jsonStr, ob.getOcapiResponse().getCustomer_id(), ob.getOcapiResponse().getC_basketId(),
						date_update, ob.getOcapiResponse().getC_brmId());
				return true;
			} else {
				log.info("fallo al cuncimir el servicio ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public RespnceModel createResponce(EntranceLoginModel elm, RespLoginModel rlm) {
		log.info(rlm.toString());
		RespnceModel rmResponce = new RespnceModel();
		ResEncryModel emBrm = null;
		ResEncryModel emCmr = null;
		try {
			if (rlm.getOcapiResponse() != null) {
				emBrm = resClientEncrypt.getResponceClient(elm, rlm.getOcapiResponse().getC_brmId(), 1);
				emCmr = resClientEncrypt.getResponceClient(elm, rlm.getOcapiResponse().getCustomer_id(), 1);

				RespnceModel.OcapiResponse ocapResponce = new RespnceModel.OcapiResponse();
				Result encriptIdBrm = new Result(emBrm.getDatos().get(0).getDato().toString(),
						emBrm.getIdAccessReturn());
				Result encriptCmrID = new Result(emCmr.getDatos().get(0).getDato().toString(),
						emBrm.getIdAccessReturn());

				ocapResponce.setAccess_token(rlm.getOcapiResponse().getAccess_token());
				ocapResponce.setC_basketId(rlm.getOcapiResponse().getC_basketId());
				ocapResponce.setC_brmId(encriptIdBrm);
				ocapResponce.setCustomer_email(rlm.getOcapiResponse().getCustomer_email());
				ocapResponce.setCustomer_id(encriptCmrID);
				ocapResponce.setCustomer_name(rlm.getOcapiResponse().getCustomer_name());
				ocapResponce.setRefresh_token(rlm.getOcapiResponse().getRefresh_token());

				rmResponce.setAction(rlm.getAction());
				rmResponce.setLocale(rlm.getLocale());
				rmResponce.setProxiedApi(rlm.getProxiedApi());
				rmResponce.setOcapiResponse(ocapResponce);
				return rmResponce;
			} else {
				log.info("fallo al consumir ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private String getDesincrypt(EntranceLoginModel dato, String acces) {
		RespnceModel rmResponce = new RespnceModel();
		ResEncryModel emBrm = null;
		String resp = "";

		try {
			emBrm = resClientEncrypt.getResponceClient(dato, acces, 2);
			resp = emBrm.getDatos().get(0).getDato();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}

}
