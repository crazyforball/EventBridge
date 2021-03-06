package com.emsrepo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.emsrepo.dao.LoggerDao;
import com.emsrepo.domain.Logger;
import com.emsrepo.service.LoggerService;

@Service("loggerService")
@Transactional
public class LoggerServiceImpl implements LoggerService {

	@Autowired
	private LoggerDao loggerDao;
	
	@Override
	@Transactional
	public List<Logger> getLoggerDetailList() {
		List<Logger> voList = new ArrayList<>();
		voList = loggerDao.getAllLoggerList();
		if (CollectionUtils.isEmpty(voList)) {
			return null;
		}
		return voList;
	}

	@Override
	@Transactional
	public void addLog(Logger logger) {
		loggerDao.saveLog(logger);
	}
}
