package com.emsrepo.enums;

public enum UserTypeEnum {

	STANDARD_USER(1, "Standard User"),
	ADMINISTRATOR(2, "Administrator");
	
	private UserTypeEnum(int utype, String desc) {
		this.utype = utype;
		this.desc = desc;
	}
	
	public int utype;
	public String desc;
	
	public static UserTypeEnum getUserTypeById(int utype) {
		for (UserTypeEnum ut : UserTypeEnum.values()) {
			if (ut.utype == utype)
				return ut;
		}
		return null;
	}
}
