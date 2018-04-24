package com.xx.leave.department.service;

import java.util.List;

import com.xx.leave.department.domain.Department;


public interface DepartmentService {
	public int deleteDepartment(String id);
	public int insertDepartment(Department department);
	public List<Department> queryDepartment();
	public List<Department> queryBySid(String sid);
}
