package com.xx.leave.section.servie;

import java.util.List;

import com.xx.leave.section.domain.Section;

public interface SectionService {
	public int deleteSection(String id);
	public int insertSection(Section section);
	public List<Section> querySection();
}
