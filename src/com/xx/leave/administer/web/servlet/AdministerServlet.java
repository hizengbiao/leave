package com.xx.leave.administer.web.servlet;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.leave.administer.domain.Administer;
import com.xx.leave.administer.service.AdministerService;
import com.xx.leave.administer.service.impl.AdministerServiceImpl;
import com.xx.leave.utils.commom.CommonUtils;
import com.xx.leave.utils.servlet.BaseServlet;


public class AdministerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    private AdministerService administerService=new AdministerServiceImpl();
    /**
     * ����Ա��¼
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Administer form = CommonUtils.toBean(request.getParameterMap(), Administer.class);
    	String username=form.getaId();
        String password=form.getaPassword();
        //��һ��map��ȡ�����У����Ϣ
        Map<String,String> map=new HashMap<String,String>();
        if(username==null||username.trim().isEmpty()){
            map.put("aId", "�û�������Ϊ��");
        }

        if(password==null||password.trim().isEmpty()){
            map.put("aPassword", "���벻��Ϊ��");
        }

        //�ж�map�ĳ��� �Ӷ��ж��Ƿ��д���
        if(map.size()>0){
            request.setAttribute("errors", map);
            request.setAttribute("user", form);
            return "f:/jsp/admin/login.jsp";
        }
    	Administer i = administerService.login(form);
    	if(i!=null){
    		request.getSession().setAttribute("user", i);
    		return "r:/jsp/main.jsp";
    	}else{
    		request.setAttribute("msg", "�˺��������");
    		return "f:/jsp/admin/login.jsp";
    	}
    
    	
    }
}
