package com.emsrepo.enums;

public enum LogTypeEnum {

	OPT_USER("USER"),
	OPT_EVENT("EVENT");
	
	private LogTypeEnum(String type){
		this.type = type;
	}
	
	public String type;
}
