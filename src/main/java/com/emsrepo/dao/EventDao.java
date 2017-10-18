package com.emsrepo.dao;

import java.util.List;

import com.emsrepo.entity.Event;

public interface EventDao {

	public List<Event> getAllEventList();

	void batchUpdateEventStatus(List<Integer> eidList, String status);
	
}
