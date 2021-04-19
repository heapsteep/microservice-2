package com.heapsteep.bean;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Microservice2 {
	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	private String environment;
	private BigDecimal quantity;
	private BigDecimal toatlCalculatedAmount;
}
