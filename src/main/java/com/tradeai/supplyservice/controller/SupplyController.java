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
import com.tradeai.supplyservice.helper.SupplyServiceHeper;
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

	@Autowired
	private SupplyServiceHeper helper;

	@GetMapping(path = "/hello")
	public String hello() {

		return "hello world";

	}

	@GetMapping("/security/{secId}/date/{processingDate}")

	public ResponseEntity<List<SupplyPositionResponse>> getSupplyForSecurityAndBUsinessDate(@PathVariable("secId") String secId,
			@PathVariable("processingDate") String processingDate) {

		List<SupplyDTO> supplyDTOs = service.getSupplyForSecurityAndBusinessDate(secId, processingDate);

		List<SupplyPositionResponse> responseList = helper.convertListDTOToResponse(supplyDTOs);

		return new ResponseEntity<List<SupplyPositionResponse>>(responseList, HttpStatus.OK);

	}
	
	////introduce a new method that gets by supply if 
	
	/// end of method 

	@GetMapping(path = "/{supplierId}/date/{processingDate}")
	public ResponseEntity<List<SupplyPositionResponse>> test(@PathVariable("supplierId") String supplierId,
			@PathVariable("processingDate") String processingDate) {

		List<SupplyDTO> supplyDTOs = service.getAllSuppliesForSupplierForDate(supplierId, processingDate);

		List<SupplyPositionResponse> responseList = helper.convertListDTOToResponse(supplyDTOs);

		return new ResponseEntity<List<SupplyPositionResponse>>(responseList, HttpStatus.OK);

	}

	@GetMapping(path = "/{supplierId}/account/{accountId}/date/{processingDate}")

	public ResponseEntity<SupplyPositionResponse> getSupplyPositionOnProcessingDateAndAccount(
			@PathVariable("supplierId") String supplierId, @PathVariable("processingDate") String processingDate,
			@PathVariable("accountId") String accountId) {

		return null;

	}

	@PostMapping

	public ResponseEntity<SupplyPositionResponse> createSupplyPosition(
			@Valid @RequestBody SupplyPositionRequest supplyPosReq) throws ParseException {

		SupplyDTO dto = new SupplyDTO();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("America/New_York"));

		dto.setSupplierId(supplyPosReq.getSupplierId());
		dto.setSupplyId(service.getMaxSupplyId() + 1);
		dto.setSupplyGroupId(service.getMaxSupplyBatchId() + 1);
		dto.setSecurityCode(supplyPosReq.getSecurityId());
		dto.setQuantity(Integer.parseInt(supplyPosReq.getQuantity()));

		dto.setSupplyDate(format.parse(supplyPosReq.getDateOfSupply()));

		SupplyDTO returnedDto = service.setSupplyForSupplierAccountAndDate(dto);

		SupplyPositionResponse responseList = helper.convertToDto(returnedDto);

		return new ResponseEntity<SupplyPositionResponse>(responseList, HttpStatus.OK);

	}

	@PostMapping(path = "/batch")

	public ResponseEntity<List<SupplyPositionResponse>> createSupplyPositions(
			@Valid @RequestBody List<SupplyPositionRequest> supplyPosReq) throws ParseException {

		List<SupplyDTO> list = new ArrayList<>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("America/New_York"));

		//List<SupplyPosition> supplyPositions = supplyPosReq.getPositions();

		Integer supplyId = service.getMaxSupplyId() ;

		Integer supplyGroupId = service.getMaxSupplyBatchId() + 1;

		for (SupplyPositionRequest postion : supplyPosReq) {

			SupplyDTO dto = new SupplyDTO();
			dto.setSupplierId(postion.getSupplierId());
			dto.setSupplyId(supplyId ++ );
			dto.setSupplyGroupId(supplyGroupId);
			dto.setSecurityCode(postion.getSecurityId());
			dto.setQuantity(Integer.parseInt(postion.getQuantity()));
			dto.setSupplyDate(format.parse(postion.getDateOfSupply()));
			list.add(dto);

		}

		List<SupplyDTO> returnedDtos = service.setSuppliesForSupplierAndDate(list);

		List<SupplyPositionResponse> responseList = helper.convertListDTOToResponse(returnedDtos);

		return new ResponseEntity<List<SupplyPositionResponse>>(responseList, HttpStatus.OK);

	}

}
