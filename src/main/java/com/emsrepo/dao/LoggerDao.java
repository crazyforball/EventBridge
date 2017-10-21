package com.emsrepo.dao;

import java.util.List;

import com.emsrepo.entity.Logger;

public interface LoggerDao {

	public List<Logger> getAllLoggerList();
	
	public void addLog(Logger log);
}
