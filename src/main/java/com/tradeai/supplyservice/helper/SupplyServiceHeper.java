package com.tradeai.supplyservice.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.tradeai.supplyservice.dto.SupplyDTO;


import com.tradeai.supplyservice.response.SupplyPositionResponse;

public class SupplyServiceHeper {
	
	public  List<SupplyPositionResponse> convertListDTOToResponse(List<SupplyDTO> dto) {


		List<SupplyPositionResponse> response = new ArrayList<>();
		
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


		dto.forEach(element  -> {
			
			SupplyPositionResponse positionResponse = new SupplyPositionResponse();

			positionResponse.setSupplierId(element.getSupplierId());
			positionResponse.setSupplyDate(dateFormat.format(element.getSupplyDate()));
			positionResponse.setSupplyGroupId(element.getSupplyGroupId());
			positionResponse.setSecurityCode(element.getSecurityCode());
			positionResponse.setQuantity(element.getQuantity());
			positionResponse.setSupplyId(element.getSupplyId());
			positionResponse.setRate(element.getRate());

			response.add(positionResponse);
			

		});
		



		return response;

	}

	public  SupplyPositionResponse convertToDto(SupplyDTO post) {
		
		ModelMapper modelMapper= new ModelMapper();

		SupplyPositionResponse postDto = modelMapper.map(post, SupplyPositionResponse.class);

		return postDto;
	}

}
