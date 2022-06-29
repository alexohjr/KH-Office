package com.khacademy.khoffice.schedule.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.schedule.models.ScheduleDTO;

public class ScheduleDAO extends SqlSessionDaoSupport {

	public int add(String string, ScheduleDTO scheduleDTO) {
		System.out.println(scheduleDTO.getSubject());
		return getSqlSession().insert(string, scheduleDTO);
	}

	public List<ScheduleDTO> scheduleList(String string, Map<Object, Object> map) {
		return getSqlSession().selectList(string, map);
	}

	public List<ScheduleDTO> projectList(String string, Map<Object, Object> map) {
		return getSqlSession().selectList(string, map);
	}
	public List<ScheduleDTO> vacationList(String string, Map<Object, Object> map) {
		return getSqlSession().selectList(string, map);
	}

	public int scheduleCount(String string, Map<Object, Object> map) {
		return getSqlSession().selectOne(string, map);
	}

	public int projectCount(String string, Map<Object, Object> map) {
		return getSqlSession().selectOne(string, map);
	}
	public int vacationCount(String string, Map<Object, Object> map) {
		return getSqlSession().selectOne(string, map);
	}
	
	public void scheduleDeletr(String string, Map<Object, Object> map) {
		getSqlSession().delete(string,map);
	}

}
