package com.tradeai.supplyservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplyPosition {
	
	private String securityId;
	private String quantity;

}
