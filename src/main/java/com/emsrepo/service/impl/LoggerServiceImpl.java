package com.emsrepo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emsrepo.dao.LoggerDao;
import com.emsrepo.entity.Logger;
import com.emsrepo.service.LoggerService;

@Service("loggerService")
public class LoggerServiceImpl implements LoggerService {

	@Autowired
	private LoggerDao loggerDao;
	
	@Override
	public List<Logger> getLoggerDetailList() {
		List<Logger> voList = new ArrayList<>();
		voList = loggerDao.getAllLoggerList();
		if (CollectionUtils.isEmpty(voList)) {
			return null;
		}
		return voList;
	}

}
