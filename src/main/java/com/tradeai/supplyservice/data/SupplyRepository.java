package com.tradeai.supplyservice.data;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tradeai.supplyservice.datamodel.Supply;

public interface SupplyRepository extends CrudRepository<Supply, Integer> {
	
	public List<Supply> findBySupplierIdAndSupplyDate(String supplyId, Date supplyDate);
	
	@Query("select max(supplyId) from Supply")
	public Integer getMaxSupplyId();

}
