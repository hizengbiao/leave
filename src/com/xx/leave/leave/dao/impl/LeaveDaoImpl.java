package com.xx.leave.leave.dao.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xx.leave.leave.dao.LeaveDao;
import com.xx.leave.leave.domain.Leave;
import com.xx.leave.leave.domain.LeaveDays;
import com.xx.leave.utils.commom.PageBean;
import com.xx.leave.utils.jdbc.TxQueryRunner;
import com.xx.leave.worker.domain.Worker;

public class LeaveDaoImpl implements LeaveDao {

	private QueryRunner qr = new TxQueryRunner();

	public int addLeave(Leave leave) {
		String sql = "insert into `leave` values(?,?,?,?,?,?,?,?,?)";
		try {
			Object[] params = { null, leave.getwId(), leave.getStartTime(),
					leave.getEndTime(), leave.getRemark(), 1, leave.getwName(),
					null, null };
			return qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int shenLeave(Worker worker, String id) {
		String sql = "update `leave` set state=?,sTime=?,sNames=? where id=?";
		try {
			Object[] params = { 2, new Date(), worker.getwName(), id };
			return qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int shenXiaoLeave(String id) {
		String sql = "update `leave` set state=? where id=?";
		try {
			Object[] params = { 4, id };
			return qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int xiaoShenLeave(String id) {
		String sql = "update `leave` set state=? where id=?";
		try {
			Object[] params = { 5, id };
			return qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public PageBean<Leave> findCd(Leave leave, int pc, int ps)
			throws SQLException {
		PageBean<Leave> leaveList = new PageBean<Leave>();
		leaveList.setPageCode(pc);
		leaveList.setPageSize(ps);
		// 重用
		StringBuilder sb = new StringBuilder("SELECT count(*) from `leave`");
		StringBuilder where = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();
		String wId = leave.getwId();
		if (wId != null && !wId.trim().isEmpty()) {
			where.append(" and wId like ?");
			params.add("%" + wId + "%");
		}

		String name = leave.getwName();
		if (name != null && !name.trim().isEmpty()) {
			where.append(" and wName like ?");
			params.add("%" + name + "%");
		}
		Integer state = leave.getState();
		if (state != null && state != 0) {
			where.append(" and state like ?");
			params.add("%" + state + "%");
		}
		Number number = (Number) qr.query(sb.append(where).toString(),
				new ScalarHandler(), params.toArray());
		int totalRecord = number.intValue();
		leaveList.setTotalRecord(totalRecord);
		StringBuilder sql = new StringBuilder("SELECT * from `leave` ");
		StringBuilder limit = new StringBuilder(" limit ?,?");
		params.add((pc - 1) * ps);
		params.add(ps);
		List<Leave> list = qr.query(sql.append(where).append(limit).toString(),
				new BeanListHandler<Leave>(Leave.class), params.toArray());
		leaveList.setPagelist(list);
		return leaveList;
	}

	public PageBean<Leave> findCd1(Leave leave, int pc, int ps)
			throws SQLException {
		PageBean<Leave> leaveList = new PageBean<Leave>();
		leaveList.setPageCode(pc);
		leaveList.setPageSize(ps);
		// 重用
		StringBuilder sb = new StringBuilder("SELECT count(*) from `leave`");
		StringBuilder where = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();
		String wId = leave.getwIds();
		if (wId != null && !wId.trim().isEmpty()) {
			where.append(" and wId like ?");
			params.add("%" + wId + "%");
		}

		String name = leave.getwNames();
		if (name != null && !name.trim().isEmpty()) {
			where.append(" and wName like ?");
			params.add("%" + name + "%");
		}
		Integer state = leave.getStates();
		if (state != null && state != 0) {
			where.append(" and state like ?");
			params.add("%" + state + "%");
		}
		Number number = (Number) qr.query(sb.append(where).toString(),
				new ScalarHandler(), params.toArray());
		int totalRecord = number.intValue();
		leaveList.setTotalRecord(totalRecord);
		StringBuilder sql = new StringBuilder("SELECT * from `leave` ");
		StringBuilder limit = new StringBuilder(" limit ?,?");
		params.add((pc - 1) * ps);
		params.add(ps);
		List<Leave> list = qr.query(sql.append(where).append(limit).toString(),
				new BeanListHandler<Leave>(Leave.class), params.toArray());
		leaveList.setPagelist(list);
		return leaveList;
	}

	public PageBean<LeaveDays> findLeaveDays(Leave leave, int pc, int ps)
			throws SQLException, ParseException {
		PageBean<LeaveDays> leaveList = new PageBean<LeaveDays>();
		leaveList.setPageCode(pc);
		leaveList.setPageSize(ps);

		StringBuilder where = new StringBuilder(" ");
		// List<Object> params = new ArrayList<Object>();
		String wId = leave.getwIds();
		if (wId != null && !wId.trim().isEmpty()) {
			where.append(" and wId like ");
			where.append("'%" + wId + "%'");
		}

		String name = leave.getwNames();

		if (name != null && !name.trim().isEmpty()) {
			where.append(" and wName like ");
			where.append("'%" + name + "%'");
		}

		// 重用
		/*
		 * StringBuilder sb=new StringBuilder("SELECT count(*) from `leave`");
		 * StringBuilder where=new StringBuilder(" where 1=1"); List<Object>
		 * params =new ArrayList<Object>(); String wId=leave.getwIds();
		 * if(wId!=null&&!wId.trim().isEmpty()){
		 * where.append(" and wId like ?"); params.add("%"+wId+"%"); }
		 * 
		 * String name = leave.getwNames();
		 * if(name!=null&&!name.trim().isEmpty()){
		 * where.append(" or wName like ?"); params.add("%"+name+"%"); }
		 */
		/*
		 * Integer state = leave.getStates(); if(state!=null && state !=0){
		 * where.append(" and state like ?"); params.add("%"+state+"%"); }
		 */
		/*
		 * Number number=(Number)qr.query(sb.append(where).toString(), new
		 * ScalarHandler(),params.toArray()); int totalRecord=number.intValue();
		 * leaveList.setTotalRecord(totalRecord);
		 */
		/*
		 * StringBuilder sql=new StringBuilder("SELECT * from `leave` ");
		 * StringBuilder limit=new StringBuilder(" limit ?,?");
		 * params.add((pc-1)*ps); params.add(ps);
		 */

		StringBuilder sql = new StringBuilder(
				"SELECT w.wId,w.wName FROM `worker` w WHERE w.auth in ('1','2')");
		String sql2 = "SELECT l.startTime,l.endTime FROM `leave` l where l.wId=?";

		/*
		 * List<Wids> list= qr.query(sql, new
		 * BeanListHandler<Wids>(Wids.class)); for(Wids s:list){
		 * System.out.printf(s.getWid()+"\n"); }
		 */

		List<LeaveDays> lt = new ArrayList<LeaveDays>();

		System.out.println(sql.append(where).toString());

		List<Object[]> list = qr.query(sql.append(where).toString(),
				new ArrayListHandler());

		int totalRecord = list.size();
		leaveList.setTotalRecord(totalRecord);

		List<List<Object[]>> list2 = new ArrayList<List<Object[]>>();
		for (Object[] o : list) {
			LeaveDays dt = new LeaveDays();
			dt.setwId(o[0].toString());
			dt.setwName(o[1].toString());
			lt.add(dt);
			// System.out.println(o[0]);
			List<Object[]> l = qr.query(sql2, new ArrayListHandler(),
					o[0].toString());
			// if(!l.isEmpty()){
			list2.add(l);
			// }
			// System.out.printf("l.size:\t%d\n",l.size());
		}

		// System.out.printf("list2.size:\t%d\n",list2.size());

		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String year = leave.getYear();
		Date startTime=new Date();
		Date endTime=new Date();
		boolean timeCondition = false;
		if (year != null && !year.trim().isEmpty()) {
			timeCondition = true;
			startTime = dfs.parse(year + "-01-01 00:00:00");
			endTime = dfs.parse(year + "-12-31 00:00:00");
		}

		int index = 0;
		for (List<Object[]> l : list2)
			try {
				{
					if (!l.isEmpty()) {
						{
							long day1 = 0;
							for (Object[] o : l) {

								Date begin = dfs.parse(o[0].toString());
								Date end = dfs.parse(o[1].toString());
								
								long between;

								if (timeCondition == true) {
									if(((begin.getTime()-endTime.getTime())>0)||((startTime.getTime()-end.getTime())>0))
									between=0;
									else if(((startTime.getTime()-begin.getTime())>=0)&&((end.getTime()-endTime.getTime())<=0)){
										between = (end.getTime() - startTime
												.getTime()+1) / 1000;// 除以1000是为了转换成秒
									}
									else if(((begin.getTime()-startTime.getTime())>=0)&&((end.getTime()-endTime.getTime())>=0)){
										between = (endTime.getTime() - begin
												.getTime()+1) / 1000;// 除以1000是为了转换成秒
									}
									else if(((startTime.getTime()-begin.getTime())>=0)&&((end.getTime()-endTime.getTime())>=0)){
										between = (endTime.getTime() - startTime
												.getTime()+1) / 1000;// 除以1000是为了转换成秒
									}
									else{
										between = (end.getTime() - begin
												.getTime()+1) / 1000;// 除以1000是为了转换成秒
									}
								} else {
									between = (end.getTime() - begin
											.getTime()+1) / 1000;// 除以1000是为了转换成秒
								}
								day1 += between / (24 * 3600);
								lt.get(index).setLeaveDays((int) day1);
								// System.out.printf("%s\t%s\t%d\n",
								// o[0],o[1],day1);
							}
						}
					} else {
						lt.get(index).setLeaveDays(0);
					}
					index++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		Collections.sort(lt);

		List<LeaveDays> ltd = new ArrayList<LeaveDays>();
		for (int i = (pc - 1) * ps; (i < (pc - 1) * ps + ps)
				&& (i < totalRecord); i++) {
			ltd.add(lt.get(i));
		}

		leaveList.setPagelist(ltd);
		return leaveList;
	}

	public Leave prints(String id) {
		String sql = "SELECT w.id,w.wId,w.wName,w.wSex,w.wTel,w.wPhone,"
				+ "du.dName AS duName,d.dName,w.auth,w.wPassword,s.sName,"
				+ "l.startTime,l.endTime,l.remark,l.sTime,l.sNames "
				+ "FROM worker w,duty du,department d,section s,`leave` l "
				+ "WHERE w.dId=du.id  " + "AND w.departmentId=d.id "
				+ "AND d.sId=s.id " + "AND l.wId=w.wId " + "AND l.id=?";
		try {
			return qr.query(sql, new BeanHandler<Leave>(Leave.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void xiao(String id) {
		String sql = "update `leave` set state=? where id=?";
		try {
			Object[] params = { 3, id };
			qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
