package com.xx.leave.department.dao;

import java.util.List;

import com.xx.leave.department.domain.Department;

public interface DepartmentDao {
	public int deleteDepartment(String id);
	public int insertDepartment(Department department);
	public List<Department> queryDepartment();
	public List<Department> queryBySid(String sid);
}
