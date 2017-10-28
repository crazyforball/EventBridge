package com.emsrepo.dao;

import java.util.List;

import com.emsrepo.domain.Logger;

public interface LoggerDao {

	public List<Logger> getAllLoggerList();
	
	public void addLog(Logger log);
}
