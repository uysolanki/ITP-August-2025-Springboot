package com.itp.ITPAugustSpringboot.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
	
	MENSCLOTTHING("men's clothing"),
	WOMENSCLOTTHING("women's clothing"),
	ELECTRONICS("electronics"),
	JEWELERY("jewelery");
	
	private String value;

	private Category(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
	
	

}
