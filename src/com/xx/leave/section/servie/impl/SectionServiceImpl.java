package com.xx.leave.section.servie.impl;

import java.util.List;

import com.xx.leave.section.dao.SectionDao;
import com.xx.leave.section.dao.impl.SectionDaoImpl;
import com.xx.leave.section.domain.Section;
import com.xx.leave.section.servie.SectionService;

public class SectionServiceImpl implements SectionService {
	
	private SectionDao sectionDao=new SectionDaoImpl();

	public int deleteSection(String id) {
		
		return sectionDao.deleteSection(id);
	}

	public int insertSection(Section section) {
		Section section2 = sectionDao.selectSectionByName(section);
		if(section2==null){
			return sectionDao.insertSection(section);
		}
		return -1;
		
	}

	public List<Section> querySection() {
		
		return sectionDao.querySection();
	}

}
