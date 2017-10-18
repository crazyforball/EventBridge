package com.emsrepo.service;

import java.util.List;

import com.emsrepo.entity.Event;
import com.emsrepo.vo.EventVO;

public interface EventService {

	public List<EventVO> getAllEventList() throws Exception;
	
	public void batchUpdateEventStatus(List<Integer> eidList, String status);
	
}
