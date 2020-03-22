package com.tradeai.supplyservice.helper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.tradeai.supplyservice.dto.SupplyDTO;
import com.tradeai.supplyservice.request.SupplyPosition;
import com.tradeai.supplyservice.response.SupplyPositionResponse;

public class SupplyServiceHeper {
	
	public  SupplyPositionResponse convertListDTOToResponse(List<SupplyDTO> dto) {


		SupplyPositionResponse positionResponse = new SupplyPositionResponse();
		positionResponse.setSupplies(new ArrayList<>());

		dto.forEach(arg0 -> {

			positionResponse.setSupplierId(arg0.getSupplierId());
			positionResponse.setSupplyDate(arg0.getSupplyDate());
			SupplyPosition position = new SupplyPosition();
			position.setSecurityId(arg0.getSecurityCode());
			position.setQuantity(arg0.getQuantity().toString());
			positionResponse.getSupplies().add(position);
			

		});
		



		return positionResponse;

	}

	public  SupplyPositionResponse convertToDto(SupplyDTO post) {
		
		ModelMapper modelMapper= new ModelMapper();

		SupplyPositionResponse postDto = modelMapper.map(post, SupplyPositionResponse.class);

		return postDto;
	}

}
