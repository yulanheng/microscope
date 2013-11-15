package com.vipshop.microscope.mysql.factory;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vipshop.microscope.mysql.dao.MsgReportDao;
import com.vipshop.microscope.mysql.dao.TraceReportDao;

public class DaoFactory {
	
	public static final MsgReportDao MSG;
	public static final TraceReportDao TRACE;
	
	static {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext-database.xml", DaoFactory.class);
		
		MSG = context.getBean(MsgReportDao.class);
		TRACE = context.getBean(TraceReportDao.class);
		
		context.close();
	}
}
