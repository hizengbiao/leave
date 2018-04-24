package com.xx.leave.department.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xx.leave.department.dao.DepartmentDao;
import com.xx.leave.department.domain.Department;
import com.xx.leave.utils.jdbc.TxQueryRunner;

public class DepartmentDaoImpl implements DepartmentDao {
	
	QueryRunner qr=new TxQueryRunner();

	public int deleteDepartment(String id) {
		
		String sql="delete from department where id=?";
		try {
			return qr.update(sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int insertDepartment(Department department) {
		String sql = "insert into department values(?,?,?)";
		try {
			Object[] params={null,department.getdName(),department.getsId()};
			return qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Department> queryDepartment() {
		String sql="SELECT d.id,d.dName,s.sName FROM department d ,section s WHERE d.sId=s.id";
		try {
			return qr.query(sql, new BeanListHandler<Department>(Department.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Department> queryBySid(String sid) {
		String sql="select * from department where sId=?";
		try {
			return qr.query(sql, new BeanListHandler<Department>(Department.class),sid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
