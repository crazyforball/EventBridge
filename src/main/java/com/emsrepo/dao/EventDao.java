package com.emsrepo.dao;

import java.util.List;

import com.emsrepo.domain.Event;

public interface EventDao {

	public List<Event> getAllEventList();

	void batchUpdateEventStatus(List<Integer> eidList, String status);

	// ++++++++
	public void saveEvent(Event event);

	public Event getEvent(int eid);

	// public Event getEvent(User creator, String eventName);

	public List<Event> getEventsByCategory(String category);

	public List<Event> getLatestNEvents(int n);

	public void deleteEvent(Event event);

	public void updateEvent(Event oldEvent, Event newEvent);
}
