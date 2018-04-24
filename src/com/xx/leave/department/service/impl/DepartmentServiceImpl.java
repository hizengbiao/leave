package com.xx.leave.department.service.impl;

import java.util.List;

import com.xx.leave.department.dao.DepartmentDao;
import com.xx.leave.department.dao.impl.DepartmentDaoImpl;
import com.xx.leave.department.domain.Department;
import com.xx.leave.department.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {
	
	private DepartmentDao departmentDao=new DepartmentDaoImpl();

	public int deleteDepartment(String id) {
		
		return departmentDao.deleteDepartment(id);
	}

	public int insertDepartment(Department department) {
		
		return departmentDao.insertDepartment(department);
	}

	public List<Department> queryDepartment() {
		
		return departmentDao.queryDepartment();
	}

	public List<Department> queryBySid(String sid) {
	
		return departmentDao.queryBySid(sid);
	}

}
