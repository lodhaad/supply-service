package com.tradeai.supplyservice.datamodel;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name ="supply" , schema = "supply")

@IdClass(SupplyId.class)
public class Supply {
	
	@Id
	///@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="supply_id")
	private Integer supplyId;
	
	@Id
	@Column(name="supply_group_id")
	private Integer supplyGroupId;
	
	@Column(name="supplier_id")
	private String supplierId;
	
	
	@Column(name="supply_date")
	private Date supplyDate;
	
	@Column(name="supply_sec_code")
	private String securityCode;
	
	@Column(name="supply_quantity")
	private Integer quantity;
	
	@Column(name="supply_rate")
	private Double rate;
	

}
