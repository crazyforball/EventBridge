package com.emsrepo.utils;

import java.util.HashMap;
import java.util.Map;

public class MapResultUtil {
	
	private Map<String, Object> result = new HashMap<>();
	
	private void setSuccess(boolean doSuccess) {
		if (doSuccess) {
			result.put("code", "200");
		} else {
			result.put("code", "500");
		}
	}
	
	private void setMsg(Object obj) {
		result.put("msg", obj);
	}
	
	public Map<String, Object> getResult() {
		return result;
	}
	
}
