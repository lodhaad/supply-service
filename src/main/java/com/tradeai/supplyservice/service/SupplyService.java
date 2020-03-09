package com.tradeai.supplyservice.service;



import java.util.List;



import com.tradeai.supplyservice.dto.SupplyDTO;

public interface SupplyService {
	
	public List<SupplyDTO> getAllSuppliesForSupplierForDate (String supplierId, String date);

	public Integer getMaxSupplyId();

	public Integer getMaxSupplyGroupIDForSupplyId();

	public SupplyDTO setSupplyForSupplierAccountAndDate(SupplyDTO supplydto);
	
	public List<SupplyDTO> setSuppliesForSupplierAndDate(List<SupplyDTO> list);
	



}
