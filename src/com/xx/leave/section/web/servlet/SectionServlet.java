package com.xx.leave.section.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.leave.section.domain.Section;
import com.xx.leave.section.servie.SectionService;
import com.xx.leave.section.servie.impl.SectionServiceImpl;
import com.xx.leave.utils.commom.CommonUtils;
import com.xx.leave.utils.servlet.BaseServlet;


public class SectionServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;
     
	private SectionService sectionService=new SectionServiceImpl();
	/**
	 * 删除部门
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String id = request.getParameter("id");
		int i = sectionService.deleteSection(id);
		if(i>0){
			request.setAttribute("msg", "删除成功");
			return "f:/SectionServlet?method=query";
		}else{
			request.setAttribute("msg", "删除失败");
			return "f:/jsp/section/sectionMsg.jsp";
		}
	}
	/**
	 * 添加部门
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Section form = CommonUtils.toBean(request.getParameterMap(), Section.class);
		int i = sectionService.insertSection(form);
		if(i>0){
			request.setAttribute("msg", "添加成功");
			return "f:/jsp/section/addSection.jsp";
		}else if(i == -1){
			request.setAttribute("msg", "此部门已经存在");
			return "f:/jsp/section/addSection.jsp";
		}
		else{
			request.setAttribute("msg", "添加失败");
			return "f:/jsp/addSection.jsp";
		}
	}
	/**
	 * 查找部门
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<Section> section = sectionService.querySection();
		request.setAttribute("sectionList", section);
		return "f:/jsp/section/sectionMsg.jsp";
	}
}
