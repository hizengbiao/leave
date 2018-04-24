package com.xx.leave.leave.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xx.leave.leave.dao.LeaveDao;
import com.xx.leave.leave.domain.Leave;
import com.xx.leave.utils.commom.PageBean;
import com.xx.leave.utils.jdbc.TxQueryRunner;
import com.xx.leave.worker.domain.Worker;

public class LeaveDaoImpl implements LeaveDao {
	
	private QueryRunner qr=new TxQueryRunner();

	public int addLeave(Leave leave) {
		String sql="insert into `leave` values(?,?,?,?,?,?,?,?,?)";
		try {
			Object[] params={null,leave.getwId(),leave.getStartTime(),leave.getEndTime(),
					leave.getRemark(),1,leave.getwName(),null,null};
			return qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int shenLeave(Worker worker,String id) {
		String sql="update `leave` set state=?,sTime=?,sNames=? where id=?";
		try {
			Object[] params={2,new Date(),worker.getwName(),id};
			return qr.update(sql,params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public int shenXiaoLeave(String id) {
		String sql="update `leave` set state=? where id=?";
		try {
			Object[] params={4,id};
			return qr.update(sql,params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int xiaoShenLeave(String id) {
		String sql="update `leave` set state=? where id=?";
		try {
			Object[] params={5,id};
			return qr.update(sql,params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public PageBean<Leave> findCd(Leave leave, int pc, int ps) throws SQLException {
		PageBean<Leave> leaveList=new PageBean<Leave>();
		leaveList.setPageCode(pc);
		leaveList.setPageSize(ps);
		//����
		StringBuilder sb=new StringBuilder("SELECT count(*) from `leave`");
		StringBuilder where=new StringBuilder(" where 1=1");
		List<Object> params =new ArrayList<Object>();
		String wId=leave.getwId();
		if(wId!=null&&!wId.trim().isEmpty()){
			where.append(" and wId like ?");
			params.add("%"+wId+"%");
		}
		
		String name = leave.getwName();
		if(name!=null&&!name.trim().isEmpty()){
			where.append(" and wName like ?");
			params.add("%"+name+"%");
		}
		Integer state = leave.getState();
		if(state!=null && state !=0){
			where.append(" and state like ?");
			params.add("%"+state+"%");
		}
		Number number=(Number)qr.query(sb.append(where).toString(), new ScalarHandler(),params.toArray());
		int totalRecord=number.intValue();
		leaveList.setTotalRecord(totalRecord);
		StringBuilder sql=new StringBuilder("SELECT * from `leave` ");
		StringBuilder limit=new StringBuilder(" limit ?,?");
		params.add((pc-1)*ps);
		params.add(ps);
		List<Leave> list= qr.query(sql.append(where).append(limit).toString(), new BeanListHandler<Leave>(Leave.class),params.toArray());
		leaveList.setPagelist(list);
		return leaveList;
	}
	public PageBean<Leave> findCd1(Leave leave, int pc, int ps) throws SQLException {
		PageBean<Leave> leaveList=new PageBean<Leave>();
		leaveList.setPageCode(pc);
		leaveList.setPageSize(ps);
		//����
		StringBuilder sb=new StringBuilder("SELECT count(*) from `leave`");
		StringBuilder where=new StringBuilder(" where 1=1");
		List<Object> params =new ArrayList<Object>();
		String wId=leave.getwIds();
		if(wId!=null&&!wId.trim().isEmpty()){
			where.append(" and wId like ?");
			params.add("%"+wId+"%");
		}
		
		String name = leave.getwNames();
		if(name!=null&&!name.trim().isEmpty()){
			where.append(" and wName like ?");
			params.add("%"+name+"%");
		}
		Integer state = leave.getStates();
		if(state!=null && state !=0){
			where.append(" and state like ?");
			params.add("%"+state+"%");
		}
		Number number=(Number)qr.query(sb.append(where).toString(), new ScalarHandler(),params.toArray());
		int totalRecord=number.intValue();
		leaveList.setTotalRecord(totalRecord);
		StringBuilder sql=new StringBuilder("SELECT * from `leave` ");
		StringBuilder limit=new StringBuilder(" limit ?,?");
		params.add((pc-1)*ps);
		params.add(ps);
		List<Leave> list= qr.query(sql.append(where).append(limit).toString(), new BeanListHandler<Leave>(Leave.class),params.toArray());
		leaveList.setPagelist(list);
		return leaveList;
	}
	public Leave prints(String id) {
		String sql ="SELECT w.id,w.wId,w.wName,w.wSex,w.wTel,w.wPhone,"
				+ "du.dName AS duName,d.dName,w.auth,w.wPassword,s.sName,"
				+ "l.startTime,l.endTime,l.remark,l.sTime,l.sNames "
				+ "FROM worker w,duty du,department d,section s,`leave` l "
				+ "WHERE w.dId=du.id  "
				+ "AND w.departmentId=d.id "
				+ "AND d.sId=s.id "
				+ "AND l.wId=w.wId "
				+ "AND l.id=?";
		try {
			return qr.query(sql, new BeanHandler<Leave>(Leave.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void xiao(String id) {
		String sql="update `leave` set state=? where id=?";
		try {
			Object[] params={3,id};
			qr.update(sql,params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
