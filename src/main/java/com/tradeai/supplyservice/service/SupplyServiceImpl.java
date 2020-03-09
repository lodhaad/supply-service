package com.tradeai.supplyservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
	public List<SupplyDTO> getAllSuppliesForSupplierForDate(String supplierId, String stringDate) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		
		Date date = null ;
		try {
			date = format.parse(stringDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		if (date == null) {
			return null;
		}

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		List<Supply> supplies = repository.findBySupplierIdAndSupplyDate(supplierId, sqlDate);

		return convertToDTO(supplies);
	}

	private List<SupplyDTO> convertToDTO(List<Supply> supplies) {

		List<SupplyDTO> listOfDtoSupplies = new ArrayList<SupplyDTO>();

		for (Supply singleSupply : supplies) {

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
		
		Integer maxSupplyId = repository.getMaxSupplyId();
		
		/// new table 
		if (maxSupplyId == null) {
			maxSupplyId = 0;
		}

		return maxSupplyId;
	}

	@Override
	public Integer getMaxSupplyGroupIDForSupplyId() {

		return new Integer(0);
	}

	@Override
	public SupplyDTO setSupplyForSupplierAccountAndDate(SupplyDTO supplydto) {

		ModelMapper mapper = new ModelMapper();
		Supply supply = mapper.map(supplydto, Supply.class);

		supply = repository.save(supply);

		return mapper.map(supply, SupplyDTO.class);

	}

	@Override
	public List<SupplyDTO> setSuppliesForSupplierAndDate(List<SupplyDTO> list) {


		List<Supply> supplies = new ArrayList<>();
		ModelMapper mapper = new ModelMapper();
		
		for (SupplyDTO supplydto: list ) {
			
			Supply supply = mapper.map(supplydto, Supply.class);
			supplies.add(supply);
			
		}
		
		Iterable<Supply> suppliesIterable =  repository.saveAll(supplies);
		
		return list;
	}

}
