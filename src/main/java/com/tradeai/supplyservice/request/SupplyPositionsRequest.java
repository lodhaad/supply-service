package com.tradeai.supplyservice.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplyPositionsRequest {
	
	private String supplierId;
	private String dateOfSupply;
	private List<SupplyPosition> positions;

}
