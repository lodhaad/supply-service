package com.tradeai.supplyservice.exception;




import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ErrorMessage {
	
	private Date timestamp;
	private String messsage; 
	
	ErrorMessage (){
		
	}

	public ErrorMessage(Date date, String errorMessage) {

			this.timestamp = date;
			this.messsage=errorMessage;
	}

}

