package com.tradeai.supplyservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradeai.supplyservice.dto.SupplyDTO;
import com.tradeai.supplyservice.request.SupplyPosition;
import com.tradeai.supplyservice.request.SupplyPositionRequest;
import com.tradeai.supplyservice.request.SupplyPositionsRequest;
import com.tradeai.supplyservice.response.SupplyPositionResponse;
import com.tradeai.supplyservice.service.SupplyService;

@RestController
@RequestMapping("/supply")
public class SupplyController {

	@Autowired
	private SupplyService service;

	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(path="/{supplierId}/date/{processingDate}")
	public ResponseEntity<SupplyPositionResponse> test(@PathVariable("supplierId") String supplierId, @PathVariable("processingDate") String processingDate) {
		
		
		List<SupplyDTO> supplyDTOs = service.getAllSuppliesForSupplierForDate(supplierId, processingDate);

		SupplyPositionResponse responseList = convertListDTOToResponse(supplyDTOs);

		return new ResponseEntity<SupplyPositionResponse>(responseList, HttpStatus.OK);


	}



	private SupplyPositionResponse convertListDTOToResponse(List<SupplyDTO> dto) {


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

	private SupplyPositionResponse convertToDto(SupplyDTO post) {

		SupplyPositionResponse postDto = modelMapper.map(post, SupplyPositionResponse.class);

		return postDto;
	}

	@GetMapping(path = "/{supplierId}/account/{accountId}/date/{processingDate}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<SupplyPositionResponse> getSupplyPositionOnProcessingDateAndAccount(
			@PathVariable("supplierId") String supplierId, @PathVariable("processingDate") String processingDate,
			@PathVariable("accountId") String accountId) {

		return null;

	}
	
	
	
	
	

	@PostMapping

	public ResponseEntity<SupplyPositionResponse> createSupplyPosition(
			@Valid @RequestBody SupplyPositionRequest supplyPosReq )
			throws ParseException {

		SupplyDTO dto = new SupplyDTO();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("America/New_York"));

		dto.setSupplierId(supplyPosReq.getSupplierId());
		dto.setSupplyId(service.getMaxSupplyId() + 1);
		dto.setSupplyGroupId(0);
		dto.setSecurityCode(supplyPosReq.getSecurityId());
		dto.setQuantity(Integer.parseInt(supplyPosReq.getQuantity()));

		dto.setSupplyDate(format.parse(supplyPosReq.getDateOfSupply()));

		SupplyDTO returnedDto = service.setSupplyForSupplierAccountAndDate(dto);

		SupplyPositionResponse responseList = convertToDto(returnedDto);

		return new ResponseEntity<SupplyPositionResponse>(responseList, HttpStatus.OK);

	}
	
	
	
	@PostMapping(path = "/batch")

	public ResponseEntity<SupplyPositionResponse> createSupplyPositions(
			@Valid @RequestBody SupplyPositionsRequest supplyPosReq )
			throws ParseException {

		List<SupplyDTO> list = new ArrayList<>();


		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		
		List<SupplyPosition> supplyPositions = supplyPosReq.getPositions();
		
		Integer supplyId = service.getMaxSupplyId() + 1;
		
		Integer supplyGroupId = 0;
		
		for (SupplyPosition postion : supplyPositions) {
			
			SupplyDTO dto = new SupplyDTO();
			dto.setSupplierId(supplyPosReq.getSupplierId());
			dto.setSupplyId(supplyId);
			dto.setSupplyGroupId(supplyGroupId++ );
			dto.setSecurityCode(postion.getSecurityId());
			dto.setQuantity(Integer.parseInt(postion.getQuantity()));
			dto.setSupplyDate(format.parse(supplyPosReq.getDateOfSupply()));
			list.add(dto);
			
		}



		List<SupplyDTO> returnedDtos = service.setSuppliesForSupplierAndDate(list);

		SupplyPositionResponse responseList = convertListDTOToResponse(returnedDtos);

		return new ResponseEntity<SupplyPositionResponse>(responseList, HttpStatus.OK);
		



	}
	
	




}
