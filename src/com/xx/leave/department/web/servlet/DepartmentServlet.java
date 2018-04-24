package com.xx.leave.department.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.leave.department.domain.Department;
import com.xx.leave.department.service.DepartmentService;
import com.xx.leave.department.service.impl.DepartmentServiceImpl;
import com.xx.leave.section.domain.Section;
import com.xx.leave.section.servie.SectionService;
import com.xx.leave.section.servie.impl.SectionServiceImpl;
import com.xx.leave.utils.commom.CommonUtils;
import com.xx.leave.utils.servlet.BaseServlet;


public class DepartmentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentService departmentService=new DepartmentServiceImpl();
	private SectionService sectionService=new SectionServiceImpl();
	/**
	 * ɾ��Ժϵ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String id = request.getParameter("id");
		int i = departmentService.deleteDepartment(id);
		if(i>0){
			request.setAttribute("msg", "ɾ���ɹ�");
			return "f:/DepartmentServlet?method=query";
		}else{
			request.setAttribute("msg", "ɾ��ʧ��");
			return "f:/jsp/department/departmentMsg.jsp";
		}
	}
	/**
	 * ���ǰ���Ҳ�����Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String preInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<Section> section = sectionService.querySection();
		request.setAttribute("sectionList", section);
		return "f:/jsp/department/addDepartment.jsp";
	}
	/**
	 * ���Ժϵ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Department form = CommonUtils.toBean(request.getParameterMap(), Department.class);
		int i = departmentService.insertDepartment(form);
		if(i>0){
			request.setAttribute("msg", "��ӳɹ�");
			return "f:/DepartmentServlet?method=preInsert";
		}else{
			request.setAttribute("msg", "���ʧ��");
			return "f:/jsp/department/addDepartment.jsp";
		}
	}
	/**
	 * ����Ժϵ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<Department> department = departmentService.queryDepartment();
		request.setAttribute("departmentList", department);
		return "f:/jsp/department/departmentMsg.jsp";
	}

}
