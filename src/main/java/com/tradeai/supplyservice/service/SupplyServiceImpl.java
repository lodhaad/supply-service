package com.tradeai.supplyservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradeai.supplyservice.data.SupplyRepository;
import com.tradeai.supplyservice.datamodel.Supply;
import com.tradeai.supplyservice.dto.SupplyDTO;

@Service
public class SupplyServiceImpl implements SupplyService {

	@Autowired
	private SupplyRepository repository;
	
	 @Autowired
	 private ModelMapper modelMapper;
	
	@Override
	public List<SupplyDTO> getAllSuppliesForSupplierForDate(String supplierId, Date date) {


		
		
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		List<Supply> supplies = repository.findBySupplierIdAndSupplyDate(supplierId,sqlDate );

		return convertToDTO(supplies);
	}
	
	private List<SupplyDTO> convertToDTO (List<Supply> supplies ) {
		
		List<SupplyDTO> listOfDtoSupplies = new ArrayList<SupplyDTO>();
		
		for (Supply singleSupply : supplies) {
			
			/*
			SupplyDTO supplyDTO = new SupplyDTO();
			supplyDTO.setQuantity(singleSupply.getQuantity());
			supplyDTO.setRate(singleSupply.getRate());
			supplyDTO.setSecurityCode(singleSupply.getSecurityCode());
			supplyDTO.setSupplierId(singleSupply.getSupplierId());
			supplyDTO.setSupplyDate(singleSupply.getSupplyDate());
			supplyDTO.setSupplyGroupId(singleSupply.getSupplyGroupId());
			supplyDTO.setSupplyId(singleSupply.getSupplyId());
			
			*/
			SupplyDTO supplyDTO = convertToDto(singleSupply);
			
			listOfDtoSupplies.add(supplyDTO);
			
		}
		
		return listOfDtoSupplies;
		
		
		
	}
	
	
	private SupplyDTO convertToDto(Supply post) {
		
		
		SupplyDTO postDto = modelMapper.map(post, SupplyDTO.class);


	    return postDto;
	}

	@Override
	public Integer getMaxSupplyId() {

		return repository.getMaxSupplyId();
	}

	@Override
	public Integer getMaxSupplyGroupIDForSupplyId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SupplyDTO setSupplyForSupplierAccountAndDate(SupplyDTO supplydto) {
		
		ModelMapper mapper= new ModelMapper();
		Supply supply = mapper.map(supplydto, Supply.class);
		
		supply = repository.save(supply);
		
		return mapper.map(supply, SupplyDTO.class);
		
		
	}



}
