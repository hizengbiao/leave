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
	 * 教职工和审批员登录
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
        //用一个map存取错误的校验信息
        Map<String,String> map=new HashMap<String,String>();
        if(username==null||username.trim().isEmpty()){
            map.put("wId", "用户名不能为空");
        }

        if(password==null||password.trim().isEmpty()){
            map.put("wPassword", "密码不能为空");
        }

        //判断map的长度 从而判断是否有错误
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
    		request.setAttribute("msg", "账号密码错误");
    		return "f:/login.jsp";
    	}    	
    }
	/**
	 * 删除教职工信息
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
			request.setAttribute("msg", "删除成功");
			return "f:/WorkerServlet?method=findCd";
		}else{
			request.setAttribute("msg", "删除失败");
			return "f:/jsp/worker/workerMsg.jsp";
		}
	}
	/**
	 * 更改前查找单个信息
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
			request.setAttribute("msg", "未找到信息");
			return "f:/jsp/worker/workerMsg.jsp";
		}
		
	}
	/**
	 * 更改教职工信息
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
			request.setAttribute("msg", "修改成功");
			return "r:/WorkerServlet?method=findCd";
		}else{
			request.setAttribute("msg", "修改失败");
			return "f:/jsp/worker/workerPre.jsp";
		}
		
	}
	/**
	 * 添加前查找部门,职务信息
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
	 * 点击部门 出现院系
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
	 * 添加教职工信息
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
			request.setAttribute("msg", "此工号已经注册");
			return "f:/jsp/worker/addWorker.jsp";
		}
		int i = workerService.insertWorker(form);
		if(i>0){
			request.setAttribute("msg", "添加成功");
			return "f:/WorkerServlet?method=preInsert";
		}else{
			request.setAttribute("msg", "添加失败");
			return "f:/jsp/worker/addWorker.jsp";
		}
	}
	/**
	 * 带条件查找教职工信息
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
	 * 获取当前页数
	 * @param request
	 * @return
	 */
	public int findPageCode(HttpServletRequest request){
		String pc=request.getParameter("pc");
		if(pc==null||pc.trim().isEmpty()) return 1;
		return Integer.parseInt(pc);
		
	}
	/**
	 * 处理get请求乱码问题
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
	 * 处理多条件查询的条件问题
	 */
	private String getUrl(HttpServletRequest request) {
		String contentPath=request.getContextPath();//获取项目名
		String servletPath=request.getServletPath();//获取请求的servlet
		String queryPath=request.getQueryString();//获取参数
		
		/*
		 * 去除当前页pc的问题
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
	 * 查找自己的信息
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
			request.setAttribute("msg", "未找到信息");
			return "f:/jsp/worker/workerSingle.jsp";
		}
		
	}
	/**
	 * 修改密码
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
			request.setAttribute("msg", "原密码不正确");
			return "f:/jsp/worker/modifyPass.jsp";
		}
		
	}

}
