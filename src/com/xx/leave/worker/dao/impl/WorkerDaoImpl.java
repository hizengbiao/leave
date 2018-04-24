package com.xx.leave.worker.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xx.leave.utils.commom.PageBean;
import com.xx.leave.utils.jdbc.TxQueryRunner;
import com.xx.leave.worker.dao.WorkerDao;
import com.xx.leave.worker.domain.Worker;
import com.xx.leave.worker.domain.WorkerExt;

public class WorkerDaoImpl implements WorkerDao {
	
	
	private QueryRunner qr = new TxQueryRunner();
	
	public Worker login(String id) {
		
		String sql = "select * from worker where wId=?";
		try {
			return qr.query(sql, new BeanHandler<Worker>(Worker.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int deleteWorker(String id) {
		String sql = "delete from worker where id=?";
		try {
			return qr.update(sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int updateWorker(Worker worker) {
		String sql = "update worker set wName=?,wSex=?,wTel=?,wPhone=?,dId=?,"
				+ "departmentId=?,sId=?,auth=?,wPassword=? where id=?";
		try {
			Object[] params={worker.getwName(),worker.getwSex(),worker.getwTel(),
					worker.getwPhone(),worker.getdId(),worker.getDepartmentId(),
					worker.getsId(),worker.getAuth(),worker.getwPassword(),worker.getId()};
			return qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int insertWorker(Worker worker) {
		String sql = "insert into worker values(?,?,?,?,?,?,"
				+ "?,?,?,?,?)";
		try {
			Object[] params={null,worker.getwId(),worker.getwName(),worker.getwSex(),worker.getwTel(),
					worker.getwPhone(),worker.getdId(),worker.getDepartmentId(),
					worker.getAuth(),worker.getwPassword(),worker.getsId()};
			return qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public PageBean<WorkerExt> findCd(Worker worker, int pc, int ps)throws SQLException {
		
		PageBean<WorkerExt> workerList=new PageBean<WorkerExt>();
		workerList.setPageCode(pc);
		workerList.setPageSize(ps);
		//÷ÿ”√
		StringBuilder sb=new StringBuilder("SELECT count(*)"
				+ " FROM worker w,duty du,department d,section s "
				+ "WHERE w.dId=du.id "
				+ "AND w.departmentId=d.id "
				+ "AND d.sId=s.id");
		StringBuilder where=new StringBuilder("");
		List<Object> params =new ArrayList<Object>();
		String wId=worker.getwId();
		if(wId!=null&&!wId.trim().isEmpty()){
			where.append(" and w.wId like ?");
			params.add("%"+wId+"%");
		}
		
		String name = worker.getwName();
		if(name!=null&&!name.trim().isEmpty()){
			where.append(" and w.wName like ?");
			params.add("%"+name+"%");
		}
		String wTel = worker.getwTel();
		if(wTel!=null&&!wTel.trim().isEmpty()){
			where.append(" and w.wTel like ?");
			params.add("%"+wTel+"%");
		}
		String wPhone = worker.getwPhone();
		if(wPhone!=null&&!wPhone.trim().isEmpty()){
			where.append(" and w.wPhone like ?");
			params.add("%"+wPhone+"%");
		}
		Integer dId = worker.getdId();
		if(dId!=null && dId!=0){
			where.append(" and w.dId like ?");
			params.add("%"+dId+"%");
		}
		Integer departmentId = worker.getDepartmentId();
		if(departmentId!=null && departmentId!=0){
			where.append(" and w.departmentId like ?");
			params.add("%"+departmentId+"%");
		}
		Integer sId = worker.getsId();
		if(sId!=null && sId!=0){
			where.append(" and w.sId like ?");
			params.add("%"+sId+"%");
		}
		Integer auth = worker.getAuth();
		if(auth!=null && auth!=0){
			where.append(" and w.auth like ?");
			params.add("%"+auth+"%");
		}
		Number number=(Number)qr.query(sb.append(where).toString(), new ScalarHandler(),params.toArray());
		int totalRecord=number.intValue();
		workerList.setTotalRecord(totalRecord);
		StringBuilder sql=new StringBuilder("SELECT "
				+ "w.id,w.wId,w.wName,w.wSex,w.wTel,w.wPhone,"
				+ "du.dName as duName,d.dName,w.auth,w.wPassword,s.sName "
				+ "FROM worker w,duty du,department d,section s "
				+ "WHERE w.dId=du.id "
				+ "AND w.departmentId=d.id "
				+ "AND d.sId=s.id");
		StringBuilder limit=new StringBuilder(" limit ?,?");
		params.add((pc-1)*ps);
		params.add(ps);
		List<WorkerExt> list= qr.query(sql.append(where).append(limit).toString(), new BeanListHandler<WorkerExt>(WorkerExt.class),params.toArray());
		workerList.setPagelist(list);
		return workerList;
	}

	public Worker findById(String id) {
		String sql="select * from worker where id=?";
		try {
			
			return qr.query(sql, new BeanHandler<Worker>(Worker.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Worker findByWid(String wid) {
		String sql="select * from worker where wId=?";
		try {
			
			return qr.query(sql, new BeanHandler<Worker>(Worker.class),wid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public WorkerExt queryById(String id) {
		String sql = "SELECT "
				+ "w.id,w.wId,w.wName,w.wSex,w.wTel,w.wPhone,"
				+ "du.dName as duName,d.dName,w.auth,w.wPassword,s.sName "
				+ "FROM worker w,duty du,department d,section s "
				+ "WHERE w.dId=du.id "
				+ "AND w.departmentId=d.id "
				+ "AND d.sId=s.id"
				+ " and w.id=?";
		try {
			return qr.query(sql, new BeanHandler<WorkerExt>(WorkerExt.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int modifyPass(Integer id, String pass) {
		String sql="update worker set wPassword=? where id=?";
		try {
			return qr.update(sql,pass,id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
