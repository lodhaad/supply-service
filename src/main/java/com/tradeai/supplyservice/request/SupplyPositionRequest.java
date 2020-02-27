package com.tradeai.supplyservice.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class SupplyPositionRequest {
	
	private String supplierId;
	private String dateOfSupply;
	private String securityId;
	private String quantity;
	
	

}
