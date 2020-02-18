package com.tradeai.supplyservice.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class SupplyDTO {
	
	

	private Integer supplyId;

	private Integer supplyGroupId;
	
	private String supplierId;
	
	private Date supplyDate;
	
	private String securityCode;
	
	private Integer quantity;
	
	private Double rate;
	

}
