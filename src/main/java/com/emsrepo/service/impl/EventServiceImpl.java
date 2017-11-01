package com.emsrepo.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emsrepo.dao.EventDao;
import com.emsrepo.dao.LoggerDao;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.Logger;
import com.emsrepo.enums.LogTypeEnum;
import com.emsrepo.service.EventService;
import com.emsrepo.utils.CollectionUtils;
import com.emsrepo.utils.DateTimeUtil;
import com.emsrepo.vo.EventVO;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao eventDao;

	@Autowired
	private LoggerDao loggerDao;

	@Override
	@Transactional
	public List<EventVO> getAllEventList() throws Exception {

		List<EventVO> voList = new ArrayList<>();
		List<Event> eventList = new ArrayList<>();
		eventList = eventDao.getAllEventList();
		if (CollectionUtils.isNotEmpty(eventList)) {
			System.out.println(eventList.size());
			for (Event event : eventList) {
				if (event != null) {
					EventVO vo = convertToEventVO(event);
					voList.add(vo);
				}
			}
		}

		return voList;
	}

	@Override
	@Transactional
	public void batchUpdateEventStatus(List<Integer> eidList, String status) {
		eventDao.batchUpdateEventStatus(eidList, status);
		addLog(eidList, status, 5);
	}

	public EventVO convertToEventVO(Event event) {
		EventVO vo = new EventVO();
		vo.setEid(event.getEid());
		vo.setEventName(event.getEventName());
		vo.setDesc(event.getDescription());
		vo.setStartDate(event.getStartDate());
		vo.setEndDate(event.getEndDate());
		vo.setLocation(vo.getLocation());
		vo.setStatus(event.getStatus());
		vo.setCapacity(event.getCapacity());
		vo.setFee(event.getFees());
		vo.setCategory(event.getCategory());
		vo.setCreator(event.getCreator());
		vo.setLocation(event.getLocation());
		return vo;
	}

	public void addLog(List<Integer> eidList, String status, int adminId) {
		Logger log = new Logger();
		log.setAdminId(adminId);
		log.setEid(CollectionUtils.convertListToString(eidList));
		log.setLogType(status + " " + LogTypeEnum.OPT_EVENT);
		log.setLogDate(DateTimeUtil.getNowadayMillsTime());
		loggerDao.addLog(log);
	}

	// +++++++++++++++++++
	@Override
	public boolean postEvent(Event event) {

		// Step 1: check whether this person is already in the database
		// Step 2: if not, save this person into the database
		if (!isExistingEvent(event)) {
			System.out.println("=====>" + "in post service");
			eventDao.saveEvent(event);
			return true;
		}
		System.out.println("eventName already exits.");
		return false;
	}

	@Override
	public boolean isExistingEvent(Event event) {
		return eventDao.getEvent(event.getEid()) != null;
	}

	@Override
	public Event retrieveEvent(int eventId) {
		return eventDao.getEvent(eventId);
	}

	@Override
	public List<Event> retrieveEventsByCategory(String category) {
		return eventDao.getEventsByCategory(category);
	}

	@Override
	public List<Event> retrieveLatestNEvents(int n) {
		return eventDao.getLatestNEvents(n);
	}

	@Override
	public void deleteEvent(Event event) {
		eventDao.deleteEvent(event);
	}

	@Override
	public void updateEvent(Event oldEvent, Event newEvent) {
		eventDao.updateEvent(oldEvent, newEvent);
	}

	@Override
	public List<EventVO> retrieveEventsByKeyword(String keyword) throws Exception {
		List<EventVO> result = new ArrayList<EventVO>();
		List<EventVO> list = getAllEventList();
		for (Iterator<EventVO> iterator = list.iterator(); iterator.hasNext();) {
			EventVO e = iterator.next();
			System.out.println(e);
			if (e.getEventName().contains(keyword) || e.getLocation().contains(keyword)) {
				result.add(e);
			}
		}
		return result;
	}

}
