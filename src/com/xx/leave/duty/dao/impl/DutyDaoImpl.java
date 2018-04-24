package com.xx.leave.duty.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xx.leave.duty.dao.DutyDao;
import com.xx.leave.duty.domain.Duty;
import com.xx.leave.utils.jdbc.TxQueryRunner;

public class DutyDaoImpl implements DutyDao {
	QueryRunner qr=new TxQueryRunner();
	public List<Duty> queryDuty() {
		String sql="select * from duty";
		try {
			return qr.query(sql, new BeanListHandler<Duty>(Duty.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
