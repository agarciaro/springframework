package com.curso.spring.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryParams {
	private Integer limit = 10;
	
	public void setLimit(Integer limit) {
		if(limit != null) {
			this.limit = limit;
		}
	}
}
