package com.xx.leave.worker.service.impl;

import java.sql.SQLException;

import com.xx.leave.utils.commom.PageBean;
import com.xx.leave.worker.dao.WorkerDao;
import com.xx.leave.worker.dao.impl.WorkerDaoImpl;
import com.xx.leave.worker.domain.Worker;
import com.xx.leave.worker.domain.WorkerExt;
import com.xx.leave.worker.service.WorkerService;

public class WorkerServiceImpl implements WorkerService {
	
	private WorkerDao workerDao=new WorkerDaoImpl();

	public Worker login(Worker worker) {
		Worker login = workerDao.login(worker.getwId());
		if(login.getwPassword().equals(worker.getwPassword())){
			return login;
		}
		return null;
	}

	public int deleteWorker(String id) {
		
		return workerDao.deleteWorker(id);
	}

	public int updateWorker(Worker worker) {
		
		return workerDao.updateWorker(worker);
	}

	public int insertWorker(Worker worker) {
		
		return workerDao.insertWorker(worker);
	}

	public PageBean<WorkerExt> findCd(Worker worker, int pc, int ps) throws SQLException {
		
		return workerDao.findCd(worker, pc, ps);
	}

	public Worker findById(String id) {
		
		return workerDao.findById(id);
	}

	public WorkerExt queryById(String id) {
		return workerDao.queryById(id);
	}

	public int modifyPass(Integer id, String pass) {
		
		return workerDao.modifyPass(id, pass);
	}

	public Worker findByWid(String wid) {
		
		return workerDao.findByWid(wid);
	}

}
