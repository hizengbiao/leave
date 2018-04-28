package com.xx.leave.worker.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.leave.department.domain.Department;
import com.xx.leave.department.service.DepartmentService;
import com.xx.leave.department.service.impl.DepartmentServiceImpl;
import com.xx.leave.duty.domain.Duty;
import com.xx.leave.duty.service.DutyService;
import com.xx.leave.duty.service.impl.DutyServiceImpl;
import com.xx.leave.section.domain.Section;
import com.xx.leave.section.servie.SectionService;
import com.xx.leave.section.servie.impl.SectionServiceImpl;
import com.xx.leave.utils.commom.CommonUtils;
import com.xx.leave.utils.commom.PageBean;
import com.xx.leave.utils.servlet.BaseServlet;
import com.xx.leave.worker.domain.Worker;
import com.xx.leave.worker.domain.WorkerExt;
import com.xx.leave.worker.service.WorkerService;
import com.xx.leave.worker.service.impl.WorkerServiceImpl;

import net.sf.json.JSONArray;


public class WorkerServlet extends BaseServlet {
		
	private static final long serialVersionUID = 1L;

	private WorkerService workerService=new WorkerServiceImpl();
	private SectionService sectionService=new SectionServiceImpl();
	private DepartmentService departmentService=new DepartmentServiceImpl();
	private DutyService dutyService=new DutyServiceImpl();
	
	
	/**
	 * ��ְ��������Ա��¼
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Worker form = CommonUtils.toBean(request.getParameterMap(), Worker.class);
    	String username=form.getwId();
        String password=form.getwPassword();
        //��һ��map��ȡ�����У����Ϣ
        Map<String,String> map=new HashMap<String,String>();
        if(username==null||username.trim().isEmpty()){
            map.put("wId", "�û�������Ϊ��");
        }

        if(password==null||password.trim().isEmpty()){
            map.put("wPassword", "���벻��Ϊ��");
        }

        //�ж�map�ĳ��� �Ӷ��ж��Ƿ��д���
        if(map.size()>0){
            request.setAttribute("errors", map);
            return "f:/login.jsp";
        }
    	Worker i = workerService.login(form);
    	if(i!=null){
    		if(i.getAuth()==2){
    			request.getSession().setAttribute("user", i);
        		return "r:/jsp/main.jsp";
    			
    		}
    		request.getSession().setAttribute("user", i);
    		return "r:/jsp/main.jsp";
    	}else{
    		request.setAttribute("msg", "�˺��������");
    		return "f:/login.jsp";
    	}    	
    }
	/**
	 * ɾ����ְ����Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String id = request.getParameter("id");
		int i = workerService.deleteWorker(id);
		if(i>0){
			request.setAttribute("msg", "ɾ���ɹ�");
			return "f:/WorkerServlet?method=findCd";
		}else{
			request.setAttribute("msg", "ɾ��ʧ��");
			return "f:/jsp/worker/workerMsg.jsp";
		}
	}
	/**
	 * ����ǰ���ҵ�����Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String preEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String id = request.getParameter("id");
		Worker worker=workerService.findById(id);		
		if(worker!=null){
			List<Section> section = sectionService.querySection();
			List<Department> depart = departmentService.queryBySid(String.valueOf(worker.getsId()));
			List<Duty> duty = dutyService.queryDuty();
			request.setAttribute("sectionList", section);
			request.setAttribute("departList", depart);
			request.setAttribute("dutyList", duty);
			request.setAttribute("workerInfo", worker);
			return "f:/jsp/worker/workerPre.jsp";
		}else{
			request.setAttribute("msg", "δ�ҵ���Ϣ");
			return "f:/jsp/worker/workerMsg.jsp";
		}
		
	}
	/**
	 * ���Ľ�ְ����Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Worker form = CommonUtils.toBean(request.getParameterMap(), Worker.class);
		int i = workerService.updateWorker(form);
		if(i>0){
			request.setAttribute("msg", "�޸ĳɹ�");
			return "r:/WorkerServlet?method=findCd";
		}else{
			request.setAttribute("msg", "�޸�ʧ��");
			return "f:/jsp/worker/workerPre.jsp";
		}
		
	}
	/**
	 * ���ǰ���Ҳ���,ְ����Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String preInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<Section> section = sectionService.querySection();
		List<Duty> duty = dutyService.queryDuty();
		request.setAttribute("sectionList", section);
		request.setAttribute("dutyList", duty);
		return "f:/jsp/worker/addWorker.jsp";
	}
	/**
	 * ������� ����Ժϵ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajax(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String sid = request.getParameter("sid");
		List<Department> list = departmentService.queryBySid(sid);
		JSONArray object = JSONArray.fromObject(list);
		response.getWriter().print(object.toString());
		return "";
	}
	/**
	 * ��ӽ�ְ����Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Worker form = CommonUtils.toBean(request.getParameterMap(), Worker.class);
		Worker already=workerService.findByWid(form.getwId());
		if(already!=null){
			request.setAttribute("msg", "�˹����Ѿ�ע��");
			return "f:/jsp/worker/addWorker.jsp";
		}
		int i = workerService.insertWorker(form);
		if(i>0){
			request.setAttribute("msg", "��ӳɹ�");
			return "f:/WorkerServlet?method=preInsert";
		}else{
			request.setAttribute("msg", "���ʧ��");
			return "f:/jsp/worker/addWorker.jsp";
		}
	}
	/**
	 * ���������ҽ�ְ����Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findCd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		try {
			Worker workerList=CommonUtils.toBean(request.getParameterMap(), Worker.class);
			workerList=encoding(workerList);
			int pageCode=findPageCode(request);
			int pageSize=10;
			List<Section> section = sectionService.querySection();
			List<Duty> duty = dutyService.queryDuty();
			PageBean<WorkerExt> listbean=workerService.findCd(workerList,pageCode,pageSize);			
			listbean.setUrl(getUrl(request));
			request.setAttribute("sectionList", section);
			request.setAttribute("dutyList", duty);
			request.setAttribute("allMsg", listbean);
			return "f:/jsp/worker/workerMsg.jsp";
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

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
	 * @param worker
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private Worker encoding(Worker worker) throws UnsupportedEncodingException {
		String wId=worker.getwId();
		String name = worker.getwName();
		String wTel = worker.getwTel();
		String wPhone = worker.getwPhone();
		if(wId!=null&&!wId.trim().isEmpty()){
			wId=new String(wId.getBytes("ISO-8859-1"),"utf-8");
			worker.setwId(wId);
		}
		if(name!=null&&!name.trim().isEmpty()){
			name=new String(name.getBytes("ISO-8859-1"),"utf-8");
			worker.setwName(name);
		}
		if(wTel!=null&&!wTel.trim().isEmpty()){
			wTel=new String(wTel.getBytes("ISO-8859-1"),"utf-8");
			worker.setwTel(wTel);
		}
		if(wPhone!=null&&!wPhone.trim().isEmpty()){
			wPhone=new String(wPhone.getBytes("ISO-8859-1"),"utf-8");
			worker.setwPhone(wPhone);
		}
		return worker;
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
		if(queryPath!=null){
			if(queryPath.contains("&pc=")){
				int index=queryPath.lastIndexOf("&pc=");
				queryPath=queryPath.substring(0, index);
			}
		}
		
		//System.out.println(contentPath+servletPath+"?"+queryPath);
		return contentPath+servletPath+"?"+queryPath;
		
	}
	/**
	 * �����Լ�����Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String queryOne(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Worker worker1 = (Worker) request.getSession().getAttribute("user");
		WorkerExt worker=workerService.queryById(String.valueOf(worker1.getId()));		
		if(worker!=null){
			request.setAttribute("workerSingle", worker);
			return "f:/jsp/worker/workerSingle.jsp";
		}else{
			request.setAttribute("msg", "δ�ҵ���Ϣ");
			return "f:/jsp/worker/workerSingle.jsp";
		}
		
	}
	/**
	 * �޸�����
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modifyPass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Worker worker1 = (Worker) request.getSession().getAttribute("user");
		WorkerExt worker=workerService.queryById(String.valueOf(worker1.getId()));
		String old = request.getParameter("oldPass");
		String String = request.getParameter("newPass");
		if(old.equals(worker.getwPassword())){
			int i=workerService.modifyPass(worker1.getId(),String);
			if(i>0){
				request.setAttribute("msg", "1");
				request.getSession().invalidate();
				return "f:/jsp/worker/modifyPass.jsp";
			}else{
				request.setAttribute("msg", "error");
				return "f:/jsp/worker/modifyPass.jsp";
			}
			
		}else{
			request.setAttribute("msg", "ԭ���벻��ȷ");
			return "f:/jsp/worker/modifyPass.jsp";
		}
		
	}

}
