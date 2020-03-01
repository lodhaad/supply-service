package com.tradeai.supplyservice.response;

import java.util.Date;
import java.util.List;

import com.tradeai.supplyservice.request.SupplyPosition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SupplyPositionResponse {
	
	//private Integer supplyId;

	//private Integer supplyGroupId;
	
	private String supplierId;
	
	private Date supplyDate;
	
	
	private List<SupplyPosition> supplies;
	
	//private String securityCode;
	
	//private Integer quantity;
	
	//private Double rate;

}
