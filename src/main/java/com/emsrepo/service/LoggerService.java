package com.emsrepo.service;

import java.util.List;

import com.emsrepo.domain.Logger;

public interface LoggerService {

	public List<Logger> getLoggerDetailList();

	public void addLog(Logger logger);
}
