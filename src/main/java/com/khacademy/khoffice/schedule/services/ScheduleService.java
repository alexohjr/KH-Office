package com.khacademy.khoffice.schedule.services;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.schedule.models.ScheduleDTO;

public interface ScheduleService {
	
	public int add(ScheduleDTO scheduleDTO);
	public List<ScheduleDTO> scheduleList(Map<Object, Object> map);
	public List<ScheduleDTO> projectList(Map<Object, Object> map);
	public List<ScheduleDTO> vacationList(Map<Object, Object> map);
	public int scheduleCount(Map<Object, Object> map);
	public int projectCount(Map<Object, Object> map);
	public int vacationCount(Map<Object, Object> map);
	
	public void scheduleDelete(Map<Object, Object> map);
	

}
