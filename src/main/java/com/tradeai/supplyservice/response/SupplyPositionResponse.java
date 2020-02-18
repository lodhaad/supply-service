package com.tradeai.supplyservice.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SupplyPositionResponse {
	
	private Integer supplyId;

	private Integer supplyGroupId;
	
	private String supplierId;
	
	private Date supplyDate;
	
	private String securityCode;
	
	private Integer quantity;
	
	private Double rate;

}
