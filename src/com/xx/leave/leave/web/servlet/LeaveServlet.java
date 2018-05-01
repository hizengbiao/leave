package com.xx.leave.leave.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xx.leave.duty.domain.Duty;
import com.xx.leave.leave.domain.Leave;
import com.xx.leave.leave.domain.LeaveDays;
import com.xx.leave.leave.service.LeaveService;
import com.xx.leave.leave.service.impl.LeaveServiceImpl;
import com.xx.leave.section.domain.Section;
import com.xx.leave.utils.commom.CommonUtils;
import com.xx.leave.utils.commom.PageBean;
import com.xx.leave.utils.jdbc.TxQueryRunner;
import com.xx.leave.utils.servlet.BaseServlet;
import com.xx.leave.worker.domain.Worker;
import com.xx.leave.worker.domain.WorkerExt;



public class LeaveServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private LeaveService leaveService=new LeaveServiceImpl();
	
	
	/**
	 * ͳ��ÿ����ְ�����������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 * @throws SQLException 
	 */
//	public void leaveDays(HttpServletRequest request, HttpServletResponse response)

	public String leaveDays(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
		try {
			Leave leaveList=CommonUtils.toBean(request.getParameterMap(), Leave.class);
			leaveList=encoding(leaveList);
			int pageCode=findPageCode(request);
			int pageSize=10;
//			PageBean<Leave> listbean1=leaveService.findCd1(leaveList,pageCode,pageSize);		
			PageBean<LeaveDays> listbean=leaveService.findLeaveDays(leaveList,pageCode,pageSize);
			listbean.setUrl(getUrl(request));
			request.setAttribute("allMsg", listbean);
			
			String leaveYear=(String) request.getParameter("year");
//			System.out.println("\n\n"+leaveList.getYear()+"\n\n");
			if (leaveYear != null && !leaveYear.trim().isEmpty()) {
				request.setAttribute("year", request.getParameter("year")+"��");
			}
			else
			request.setAttribute("year", "");
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return "f:/jsp/leave/leaveDays.jsp";
	}
	
	
	
	/**
	 * ��ӡ�����
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String print(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String id = request.getParameter("id");
		int i = leaveService.print(id);
		if(i==1){
			request.setAttribute("msg", "������Ѿ����ص�D�̣���ע��鿴");
			return "f:/LeaveServlet?method=findCd";
		}else{
			request.setAttribute("msg", "error");
			return "f:/LeaveServlet?method=findCd";
		}
	}
	/**
	 * ������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Leave form = CommonUtils.toBean(request.getParameterMap(), Leave.class);
		Date startTime = form.getStartTime();
		Date now=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DATE, -1);
		now=calendar.getTime();
		if(now.after(startTime)){
			request.setAttribute("msg", "��������ȷ������");
			return "f:/jsp/leave/addLeave.jsp";
		}
		Date endTime = form.getEndTime();
//		calendar.setTime(endTime);
//		calendar.add(Calendar.DATE, 1);
//		Date endTime1=calendar.getTime();
		if(endTime.before(startTime)){
			request.setAttribute("msg", "��������ȷ������");
			return "f:/jsp/leave/addLeave.jsp";
		}
		Worker worker = (Worker) request.getSession().getAttribute("user");
		form.setwId(worker.getwId());
		form.setwName(worker.getwName());
		int i = leaveService.addLeave(form);
		if(i>0){
			request.setAttribute("msg", "��ӳɹ�");
			return "f:/jsp/leave/addLeave.jsp";
		}else{
			request.setAttribute("msg", "���ʧ��");
			return "f:/jsp/leave/addLeave.jsp";
		}
	}
	/**
	 * ������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String shenhe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String id = request.getParameter("id");
		Worker worker = (Worker) request.getSession().getAttribute("user");
		int i = leaveService.shenLeave(worker,id);
		if(i>0){
			request.setAttribute("msg", "��˳ɹ�");
			return "f:/LeaveServlet?method=findCd1";
		}else{
			request.setAttribute("msg", "���ʧ��");
			return "f:/jsp/leave/allLeaveMsg.jsp";
		}
	}
	/**
	 * ��������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String shenXiao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String id = request.getParameter("id");
		int i = leaveService.shenXiaoLeave(id);
		if(i>0){
			request.setAttribute("msg", "�ɹ�");
			return "f:/LeaveServlet?method=findCd";
		}else{
			request.setAttribute("msg", "ʧ��");
			return "f:/jsp/leave/leaveMsg.jsp";
		}
	}
	/**
	 * �������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String xiaoShen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String id = request.getParameter("id");
		int i = leaveService.xiaoShenLeave(id);
		if(i>0){
			request.setAttribute("msg", "�ɹ�");
			return "f:/LeaveServlet?method=findCd1";
		}else{
			request.setAttribute("msg", "ʧ��");
			return "f:/jsp/leave/allLeaveMsg.jsp";
		}
	}
	/**
	 * ��������ѯ������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findCd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		try {
			Leave leaveList=CommonUtils.toBean(request.getParameterMap(), Leave.class);
			Worker worker = (Worker) request.getSession().getAttribute("user");
			leaveList.setwId(worker.getwId());
			leaveList=encoding(leaveList);
			int pageCode=findPageCode(request);
			int pageSize=10;
			PageBean<Leave> listbean=leaveService.findCd(leaveList,pageCode,pageSize);			
			listbean.setUrl(getUrl(request));
			request.setAttribute("allMsg", listbean);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return "f:/jsp/leave/leaveMsg.jsp";		
	}
	public String findCd1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		try {
			Leave leaveList=CommonUtils.toBean(request.getParameterMap(), Leave.class);
			leaveList=encoding(leaveList);
			int pageCode=findPageCode(request);
			int pageSize=10;
			PageBean<Leave> listbean=leaveService.findCd1(leaveList,pageCode,pageSize);			
			listbean.setUrl(getUrl(request));
			request.setAttribute("allMsg", listbean);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return "f:/jsp/leave/allLeaveMsg.jsp";		
	}
	/**
	 * ��ȡ��ǰҳ��
	 * @param request
	 * @return
	 */
	public int findPageCode(HttpServletRequest request){
		String pc=request.getParameter("pc");
		if(pc==null||pc.trim().isEmpty()) return 1;
		return Integer.parseInt(pc);
		
	}
	/**
	 * ����get������������
	 * @param Leave
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private Leave encoding(Leave leave) throws UnsupportedEncodingException {
		String wId=leave.getwId();
		String name = leave.getwName();
		String remark = leave.getRemark();
		if(wId!=null&&!wId.trim().isEmpty()){
			wId=new String(wId.getBytes("ISO-8859-1"),"utf-8");
			leave.setwId(wId);
		}
		if(name!=null&&!name.trim().isEmpty()){
			name=new String(name.getBytes("ISO-8859-1"),"utf-8");
			leave.setwName(name);
		}
		if(remark!=null&&!remark.trim().isEmpty()){
			remark=new String(remark.getBytes("ISO-8859-1"),"utf-8");
			leave.setRemark(remark);
		}
		return leave;
	}
	/*
	 * �����������ѯ����������
	 */
	private String getUrl(HttpServletRequest request) {
		String contentPath=request.getContextPath();//��ȡ��Ŀ��
		String servletPath=request.getServletPath();//��ȡ�����servlet
		String queryPath=request.getQueryString();//��ȡ����
		
		/*
		 * ȥ����ǰҳpc������
		 */
		if(queryPath.contains("&pc=")){
			int index=queryPath.lastIndexOf("&pc=");
			queryPath=queryPath.substring(0, index);
		}
		System.out.println(contentPath+servletPath+"?"+queryPath);
		return contentPath+servletPath+"?"+queryPath;
		
	}

}
