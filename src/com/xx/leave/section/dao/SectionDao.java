package com.xx.leave.section.dao;

import java.util.List;

import com.xx.leave.section.domain.Section;

public interface SectionDao {
	public int deleteSection(String id);
	public int insertSection(Section section);
	public Section selectSectionByName(Section section);
	public List<Section> querySection();
}
