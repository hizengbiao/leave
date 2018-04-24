package com.xx.leave.administer.service.impl;

import com.xx.leave.administer.dao.AdministerDao;
import com.xx.leave.administer.dao.impl.AdministerDaoImpl;
import com.xx.leave.administer.domain.Administer;
import com.xx.leave.administer.service.AdministerService;

public class AdministerServiceImpl implements AdministerService {
	
	private AdministerDao administerDao=new AdministerDaoImpl();
	public Administer login(Administer administer){
		Administer login = administerDao.login(administer.getaId());
		if(login.getaPassword().equals(administer.getaPassword())){
			return login;
		}
		return null;
	}

}
