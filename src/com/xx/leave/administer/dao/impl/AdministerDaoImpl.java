package com.xx.leave.administer.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xx.leave.administer.dao.AdministerDao;
import com.xx.leave.administer.domain.Administer;
import com.xx.leave.utils.jdbc.TxQueryRunner;

public class AdministerDaoImpl implements AdministerDao {
	
	private QueryRunner qr = new TxQueryRunner();
	
	public Administer login(String id) {
		String sql = "select * from administer where aId=?";
		try {
			return qr.query(sql, new BeanHandler<Administer>(Administer.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
