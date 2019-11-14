package com.froyo.spring.model.dto;

import com.froyo.spring.util.messages.AbstractResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class CurrencyResponse extends AbstractResponse {

	CurrencyRequest currencyRequest = new CurrencyRequest(); 
	
	private static final long serialVersionUID = 3141555321954313455L;

}