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
	 * ɾ������
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
			request.setAttribute("msg", "ɾ���ɹ�");
			return "f:/SectionServlet?method=query";
		}else{
			request.setAttribute("msg", "ɾ��ʧ��");
			return "f:/jsp/section/sectionMsg.jsp";
		}
	}
	/**
	 * ��Ӳ���
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
			request.setAttribute("msg", "��ӳɹ�");
			return "f:/jsp/section/addSection.jsp";
		}else if(i == -1){
			request.setAttribute("msg", "�˲����Ѿ�����");
			return "f:/jsp/section/addSection.jsp";
		}
		else{
			request.setAttribute("msg", "���ʧ��");
			return "f:/jsp/addSection.jsp";
		}
	}
	/**
	 * ���Ҳ���
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
