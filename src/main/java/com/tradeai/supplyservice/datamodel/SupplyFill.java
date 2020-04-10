package com.tradeai.supplyservice.datamodel;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

//@Entity
//@Table(name = "supply_fill", schema = "supply")

public class SupplyFill {

//	@Column(name = "supply_id")
	private Integer supplyId;

//	@Column(name = "supply_group_id")
	private Integer supplyGroupId;
	
//	@Column(name = "supply_group_id")
	private Integer demand_id;
	
//	@Column(name = "used_quantity")
	private Integer usedQty;

}
