package com.tradeai.supplyservice.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;




import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SupplyPositionResponse  {
	

	private Integer supplyId;

	private Integer supplyGroupId;
	
	private String supplierId;
	
	private String supplyDate;
	
	private String securityCode;
	
	private Integer quantity;
	
	private Double rate;

}
