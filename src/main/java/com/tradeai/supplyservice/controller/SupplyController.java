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
import com.tradeai.supplyservice.request.SupplyPositionRequest;
import com.tradeai.supplyservice.response.SupplyPositionResponse;
import com.tradeai.supplyservice.service.SupplyService;

@RestController
@RequestMapping("/supply")
public class SupplyController {

	@Autowired
	private SupplyService service;

	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}

	@GetMapping(path = "/{supplierId}/date/{processingDate}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<List<SupplyPositionResponse>> getSupplyPositionOnSupplierAndProcessingDate(
			@PathVariable("supplierId") String supplierId, @PathVariable("processingDate") String processingDate)
			throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("America/New_York"));

		List<SupplyDTO> supplyDTOs = service.getAllSuppliesForSupplierForDate(supplierId, format.parse(processingDate));

		List<SupplyPositionResponse> responseList = convertListDTOToResponse(supplyDTOs);

		return new ResponseEntity<List<SupplyPositionResponse>>(responseList, HttpStatus.OK);

	}

	private List<SupplyPositionResponse> convertListDTOToResponse(List<SupplyDTO> dto) {

		List<SupplyPositionResponse> response = new ArrayList<SupplyPositionResponse>();

		dto.forEach(arg0 -> {

			SupplyPositionResponse positionResponse = convertToDto(arg0);
			response.add(positionResponse);

		});

		return response;

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
	
	
	
	
	

	@PostMapping(path = "/{supplierId}/date/{processingDate}", consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<SupplyPositionResponse> createSupplyPosition(
			@Valid @RequestBody SupplyPositionRequest supplyPosReq, @PathVariable("supplierId") String supplierId,
			@PathVariable("processingDate") String processingDate)
			throws ParseException {

		SupplyDTO dto = new SupplyDTO();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("America/New_York"));

		dto.setSupplierId(supplierId);
		//dto.setSupplyId(service.getMaxSupplyId() + 1);
		dto.setSupplyGroupId(0);
		dto.setSecurityCode(supplyPosReq.getSecurityId());
		dto.setQuantity(Integer.parseInt(supplyPosReq.getQuantity()));

		dto.setSupplyDate(format.parse(processingDate));

		SupplyDTO returnedDto = service.setSupplyForSupplierAccountAndDate(dto);

		SupplyPositionResponse responseList = convertToDto(returnedDto);

		return new ResponseEntity<SupplyPositionResponse>(responseList, HttpStatus.OK);

	}
	
	
	@PostMapping(path = "/{supplierId}/date/{processingDate}/batch", consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<List<SupplyPositionResponse>> createSupplyPositions(
			@Valid @RequestBody List<SupplyPositionRequest> supplyPosReqList, @PathVariable("supplierId") String supplierId,
			@PathVariable("processingDate") String processingDate)
			throws ParseException {

			//TODO - implement the list method with streams 

			return null;
	}


}
