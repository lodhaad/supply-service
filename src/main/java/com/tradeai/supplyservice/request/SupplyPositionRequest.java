package com.tradeai.supplyservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SupplyPositionRequest {
	
	private String securityId;
	private String quantity;

}
