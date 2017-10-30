package com.emsrepo.service;

import java.util.List;

import com.emsrepo.domain.Event;
import com.emsrepo.vo.EventVO;

public interface EventService {

	public List<EventVO> getAllEventList() throws Exception;

	public void batchUpdateEventStatus(List<Integer> eidList, String status);

	// +++++++++++++++++
	public boolean postEvent(Event event);

	public boolean isExistingEvent(Event event);

	public Event retrieveEvent(int eid);

	// public Event retrieveEvent(User creator, String eventName);

	public List<Event> retrieveEventsByCategory(String category);

	public List<EventVO> retrieveEventsByKeyword(String keyword) throws Exception;

	public List<Event> retrieveLatestNEvents(int n);

	public void deleteEvent(Event event);

	public void updateEvent(Event oldEvent, Event newEvent);

}
