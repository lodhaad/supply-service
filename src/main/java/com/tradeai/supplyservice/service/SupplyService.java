package com.tradeai.supplyservice.service;

import java.util.Date;
import java.util.List;



import com.tradeai.supplyservice.dto.SupplyDTO;

public interface SupplyService {
	
	public List<SupplyDTO> getAllSuppliesForSupplierForDate (String supplierId, Date date);

	public Integer getMaxSupplyId();

	public Integer getMaxSupplyGroupIDForSupplyId();

	public SupplyDTO setSupplyForSupplierAccountAndDate(SupplyDTO supplydto);
	



}
