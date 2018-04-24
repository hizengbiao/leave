package com.xx.leave.leave.service;

import java.sql.SQLException;

import com.xx.leave.leave.domain.Leave;
import com.xx.leave.utils.commom.PageBean;
import com.xx.leave.worker.domain.Worker;

public interface LeaveService {
	public int addLeave(Leave leave);
	public int shenLeave(Worker worker,String id);
	public int shenXiaoLeave(String id);
	public int xiaoShenLeave(String id);
	public PageBean<Leave> findCd(Leave leave,int pc,int ps) throws SQLException;
	public PageBean<Leave> findCd1(Leave leave,int pc,int ps) throws SQLException;
	public int print(String id);	

}
