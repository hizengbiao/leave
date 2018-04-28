package com.xx.leave;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.xx.leave.utils.jdbc.TxQueryRunner;

public class Days {
	/**
	 * 统计每个教职工的请假天数
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 * @throws ParseException 
	 */
//	public void leaveDays(HttpServletRequest request, HttpServletResponse response)
	public static void leaveDays()
            throws ServletException, IOException, SQLException, ParseException {
		
		QueryRunner qr=new TxQueryRunner();
		
		/*String sql ="SELECT w.wId"
				+ " FROM worker w"
				+ " WHERE w.auth='1' or w.auth='2'";*/
		
		String sql ="SELECT w.wId FROM `worker` w WHERE w.auth='1' or w.auth='2'";
		String sql2="SELECT l.startTime,l.endTime FROM `leave` l where l.wId=?";
		
		/*List<Wids> list= qr.query(sql, new BeanListHandler<Wids>(Wids.class));
		for(Wids s:list){
			System.out.printf(s.getWid()+"\n");
		}*/
		
		List<Object[]> list=qr.query(sql, new ArrayListHandler());
		List<List<Object[]>> list2=new ArrayList<List<Object[]>>();
		for(Object[] o:list){
//			System.out.println(o[0]);
			List<Object[]> l=qr.query(sql2, new ArrayListHandler(),o[0].toString());
			if(!l.isEmpty()){
				list2.add(l);
			}			
//			System.out.printf("l.size:\t%d\n",l.size());
		}
		
//		System.out.printf("list2.size:\t%d\n",list2.size());
		
		
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		for(List<Object[]> l:list2){
			for(Object[] o:l){

				java.util.Date begin=dfs.parse(o[0].toString());
				java.util.Date end = dfs.parse(o[1].toString());
				long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
				long day1=between/(24*3600);
				
//				System.out.printf("%s\t%s\t%d\n", o[0],o[1],day1);
			}
		}
		
		

	}

}
