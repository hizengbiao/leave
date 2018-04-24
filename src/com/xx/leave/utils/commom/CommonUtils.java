package com.xx.leave.utils.commom;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;


public class CommonUtils {

	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}


	@SuppressWarnings("rawtypes")
	public static <T> T toBean(Map map, Class<T> clazz) {
		try {
			T bean = clazz.newInstance();
			ConvertUtils.register((Converter) new DateConverter(), java.util.Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}