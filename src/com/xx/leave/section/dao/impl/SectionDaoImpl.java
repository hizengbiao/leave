package com.xx.leave.section.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xx.leave.section.dao.SectionDao;
import com.xx.leave.section.domain.Section;
import com.xx.leave.utils.jdbc.TxQueryRunner;

public class SectionDaoImpl implements SectionDao {
	
	QueryRunner qr=new TxQueryRunner();

	public int deleteSection(String id) {
		String exSql="delete from department where sId=?";
		String sql="delete from section where id=?";
		try {
			qr.update(exSql,id);
			return qr.update(sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int insertSection(Section section) {
		String sql = "insert into section values(?,?)";
		try {
			Object[] params={null,section.getsName()};
			return qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Section> querySection() {
		String sql="select * from section";
		try {
			return qr.query(sql, new BeanListHandler<Section>(Section.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Section selectSectionByName(Section section) {
		String sql="select * from section where sName like ?";
		try {
			String name = section.getsName();
			return qr.query(sql, new BeanHandler<Section>(Section.class),"%"+name+"%");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
