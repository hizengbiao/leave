package com.xx.leave.worker.service;

import java.sql.SQLException;

import com.xx.leave.utils.commom.PageBean;
import com.xx.leave.worker.domain.Worker;
import com.xx.leave.worker.domain.WorkerExt;

public interface WorkerService {
	public Worker login(Worker worker);
	public WorkerExt queryById(String id);
	public int deleteWorker(String id);
	public int updateWorker(Worker worker);
	public int insertWorker(Worker worker);
	public PageBean<WorkerExt> findCd(Worker worker,int pc,int ps) throws SQLException;
	public Worker findById(String id);
	public Worker findByWid(String wid);
	public int modifyPass(Integer id, String pass);

}
