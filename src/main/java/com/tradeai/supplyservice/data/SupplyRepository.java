package com.tradeai.supplyservice.data;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



import com.tradeai.supplyservice.datamodel.Supply;

public interface SupplyRepository extends CrudRepository<Supply, Integer> {
	
	public List<Supply> findBySupplierIdAndSupplyDate(String supplyId, Date supplyDate);
	
	@Query("select max(supplyId) from Supply")
	public Integer getMaxSupplyId();
	
	@Query("select max(supplyGroupId) from Supply")
	public Integer getMaxSupplyBatchId();
	
	
	public List<Supply> findBySecurityCodeAndSupplyDate(String secCode, Date supplyDate);

}
