package com.emsrepo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emsrepo.dao.EventDao;
import com.emsrepo.entity.Event;
import com.emsrepo.service.EventService;
import com.emsrepo.utils.CollectionUtils;
import com.emsrepo.vo.EventVO;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao eventDao;
	
	@Override
	@Transactional
	public List<EventVO> getAllEventList() throws Exception {
		
		List<EventVO> voList = new ArrayList<>();
		List<Event> eventList = new ArrayList<>();
		eventList = eventDao.getAllEventList();
		if (CollectionUtils.isNotEmpty(eventList)) {
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
		vo.setVacancy(event.getVacancy());
		vo.setLocation(event.getLocation());
		return vo;
	}
}
