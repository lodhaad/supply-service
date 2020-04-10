package com.tradeai.supplyservice.request;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class SupplyPositionRequest implements Serializable {
	

	private String supplierId;
	private String dateOfSupply;
	private String securityId;
	private String quantity;
	
	

}
