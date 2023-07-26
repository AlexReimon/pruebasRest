package com.middleware.newshop.integraciones.percistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepocitoryMCS extends CrudRepository<ModelEntetyMCS, String>{
	
	@Query(value = "SELECT NAME, VALUE FROM MKT_CONFIGURATION_SERVICE WHERE NAME = (COALESCE(?1,'')) ", nativeQuery = true)
	ModelEntetyMCS getConfigService(String name);

}
