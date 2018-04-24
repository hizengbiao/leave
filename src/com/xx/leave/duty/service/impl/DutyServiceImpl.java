package com.xx.leave.duty.service.impl;

import java.util.List;

import com.xx.leave.duty.dao.DutyDao;
import com.xx.leave.duty.dao.impl.DutyDaoImpl;
import com.xx.leave.duty.domain.Duty;
import com.xx.leave.duty.service.DutyService;

public class DutyServiceImpl implements DutyService {
	
	private DutyDao dutyDao=new DutyDaoImpl();
	public List<Duty> queryDuty() {
	
		return dutyDao.queryDuty();
	}

}
