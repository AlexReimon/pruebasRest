package com.middleware.newshop.integraciones.percistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QueryRepository extends  CrudRepository<ModelEntity, String>{
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO MKT_LOANS_RECORD (CUENTA_BRM, LOANS_CUPOUTILIZADO, LOANS_LINEACREDITO, LOANS_CUPOASIGNADO, LOANS_DIASMORA, LOANS_TIPOMENSAJE)VALUES(COALESCE(?1,''),COALESCE(?2,''),COALESCE(?3,''), COALESCE(?4,''), COALESCE(?5,''), COALESCE(?6,''))", nativeQuery = true)
	void Insert(String brm_id, String acces_token , String  refresh_token, String responce, String customer_id, String basket_id );
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE MKT_LOANS_RECORD SET LOANS_CUPOUTILIZADO = ?1 , LOANS_LINEACREDITO = ?2,  LOANS_CUPOASIGNADO = ?3, LOANS_DIASMORA = ?4, LOANS_TIPOMENSAJE = ?5 ,LAST_UPDATE = TO_DATE(?6, 'dd/mm/yyyy hh24:mi:ss') WHERE CUENTA_BRM = ?7 ", nativeQuery = true)
	void updateToken(String acces_token, String refresh_token, String responce,String customer_id, String basket_id, String last_update_t, String brm_id);
	
	@Query(value = "SELECT * FROM MKT_LOANS_RECORD WHERE CUENTA_BRM = (COALESCE(?1,''))", nativeQuery = true)
	ModelEntity SelectExist(String brmID);
	
	
}
