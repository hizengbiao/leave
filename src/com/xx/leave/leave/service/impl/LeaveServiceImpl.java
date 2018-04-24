package com.xx.leave.leave.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xx.leave.leave.dao.LeaveDao;
import com.xx.leave.leave.dao.impl.LeaveDaoImpl;
import com.xx.leave.leave.domain.Leave;
import com.xx.leave.leave.service.LeaveService;
import com.xx.leave.utils.commom.PageBean;
import com.xx.leave.utils.doc.DocUtils;
import com.xx.leave.worker.domain.Worker;

public class LeaveServiceImpl implements LeaveService {
	
	private LeaveDao leaveDao=new LeaveDaoImpl();

	public int addLeave(Leave leave) {
		
		return leaveDao.addLeave(leave);
	}

	public int shenLeave(Worker worker, String id) {
		
		return leaveDao.shenLeave(worker,id);
	}

	public int shenXiaoLeave(String id) {
		
		return leaveDao.shenXiaoLeave(id);
	}

	public int xiaoShenLeave(String id) {
		
		return leaveDao.xiaoShenLeave(id);
	}

	public PageBean<Leave> findCd(Leave leave, int pc, int ps) throws SQLException {
		
		return leaveDao.findCd(leave, pc, ps);
	}
	public PageBean<Leave> findCd1(Leave leave, int pc, int ps) throws SQLException {
		
		return leaveDao.findCd1(leave, pc, ps);
	}

	public int print(String id) {
		try{
		Leave leave=leaveDao.prints(id);
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		String sTime=format.format(leave.getsTime());
		String startTime=format.format(leave.getStartTime());
		String endTime=format.format(leave.getEndTime());
		DocUtils docUtil=new DocUtils();
        Map<String, Object> dataMap=new HashMap<String, Object>();
        dataMap.put("wName",leave.getwName());
        dataMap.put("wSection", leave.getsName());
        dataMap.put("wId", leave.getwId());
        dataMap.put("wDuty", leave.getDuName());
        dataMap.put("wPhone", leave.getwPhone());
        dataMap.put("startTime", startTime);
        dataMap.put("endTime", endTime);
        dataMap.put("wTel", leave.getwTel());
        dataMap.put("category", "无");
        dataMap.put("remark", leave.getRemark());
        dataMap.put("wNames", leave.getwName());
        dataMap.put("times", time);
        dataMap.put("sName", "同意请假");
        dataMap.put("sNames", leave.getsNames());
        dataMap.put("sTime", sTime);
        docUtil.createDoc(dataMap, "BaseDoc", "D:\\请假条.doc");
        leaveDao.xiao(id);
        return 1;
		}catch(Exception e){
			return 0;
		}

	}

}
