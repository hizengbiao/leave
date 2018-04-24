package com.xx.leave.leave.dao;

import java.sql.SQLException;

import com.xx.leave.leave.domain.Leave;
import com.xx.leave.utils.commom.PageBean;
import com.xx.leave.worker.domain.Worker;

public interface LeaveDao {
	public int addLeave(Leave leave);
	public int shenLeave(Worker worker,String id);
	public int shenXiaoLeave(String id);
	public int xiaoShenLeave(String id);
	public PageBean<Leave> findCd(Leave leave,int pc,int ps) throws SQLException;
	public PageBean<Leave> findCd1(Leave leave,int pc,int ps) throws SQLException;
	public Leave prints(String id);
	public void xiao(String id);	
}
