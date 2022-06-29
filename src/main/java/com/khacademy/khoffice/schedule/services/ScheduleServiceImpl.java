package com.khacademy.khoffice.schedule.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.schedule.daos.ScheduleDAO;
import com.khacademy.khoffice.schedule.models.ScheduleDTO;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	ScheduleDAO scheduleDAO;
	
	@Autowired
	@Required
	public void setScheduleDAO(ScheduleDAO scheduleDAO) {
		this.scheduleDAO = scheduleDAO;
	}

	@Override
	public int add(ScheduleDTO scheduleDTO) {
		return scheduleDAO.add("schedule.addSchedule", scheduleDTO);
	}

	@Override
	public List<ScheduleDTO> scheduleList(Map<Object, Object> map) {
		return scheduleDAO.scheduleList("schedule.scheduleList",map);
	}


	@Override
	public List<ScheduleDTO> projectList(Map<Object, Object> map) {
		return scheduleDAO.projectList("schedule.projectList", map);
	}
	
	@Override
	public List<ScheduleDTO> vacationList(Map<Object, Object> map) {
		return scheduleDAO.vacationList("schedule.vacationList", map);
	}

	@Override
	public int scheduleCount(Map<Object, Object> map) {
		return scheduleDAO.scheduleCount("schedule.scheduleCount", map);
	}

	@Override
	public int projectCount(Map<Object, Object> map) {
		return scheduleDAO.projectCount("schedule.projectCount", map);
	}

	@Override
	public int vacationCount(Map<Object, Object> map) {
		return scheduleDAO.vacationCount("schedule.vacationCount", map);
	}

	@Override
	public void scheduleDelete(Map<Object, Object> map) {
		scheduleDAO.scheduleDeletr("schedule.scheduleDelete", map);
	}
	
}
