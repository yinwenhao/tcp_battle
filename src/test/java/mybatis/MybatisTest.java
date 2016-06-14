package mybatis;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import when_how.hero.dao.entity.Test;

public class MybatisTest {
	private static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		
//		String url = "jdbc:mysql://10.128.8.36:58111/lang_resource_info?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useUnicode=true";
//		String username = "rw_user";
//		String password = "Z9a9dy3BcIMnrbf2Xfdp";
		
		String url = "jdbc:mysql://127.0.0.1:3306/hero?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
		String username = "root";
		String password = "123456";
		
		Connection conn = null;
		try {
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	private static void getAll() throws SQLException, IOException {
	    Connection conn = getConn();
//	    String sql = "SELECT   prod_id,   UNIX_TIMESTAMP(date),   price FROM   t_rs_product_calendar_price WHERE   prod_id IN ( 1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 ,10 ,11 ,12 ,13 ,14 ,15 ,16 ,17 ,18 ,19 ,20 ,21 ,22 ,23 ,24 ,25 ,26 ,27 ,28 ,29 ,30 ,31 ,32 ,33 ,34 ,35 ,36 ,37 ,38 ,39 ,40 ,41 ,42 ,43 ,44 ,45 ,46 ,47 ,48 ,49 ,50 ,51 ,52 ,53 ,54 ,55 ,56 ,57 ,58 ,59 ,60 ,61 ,62 ,63 ,64 ,65 ,66 ,67 ,68 ,69 ,70 ,71 ,72 ,73 ,74 ,75 ,76 ,77 ,78   ) AND date >= '2016-06-10' AND date <= '2016-09-07';";
//	    String sql = "SELECT   prod_id,   UNIX_TIMESTAMP(date),   price FROM   t_rs_product_calendar_price WHERE   prod_id = 1 AND date = '2016-06-10' ";
	    String sql = "SELECT   id,a,b,c FROM   test WHERE   id = 1";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
//	    try {
//	    	long start = System.currentTimeMillis();
//	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
//			System.out.println(System.currentTimeMillis() - start);
//			start = System.currentTimeMillis();
//	        rs = pstmt.executeQuery();
//			System.out.println(System.currentTimeMillis() - start);
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } finally {
//	    	rs.close();
//	    	pstmt.close();
////	    	conn.close();
//	    }
	    
	    System.out.println("aaaaaaaaaaaaaaaa");
	    try {
	    	long start = System.currentTimeMillis();
	    	Statement s = conn.createStatement();
	    	rs = s.executeQuery(sql);
	    	if (rs.next()) {
	    		System.out.println(rs.getString(1));
	    		System.out.println(rs.getString(2));
	    		System.out.println(rs.getString(3));
	    		System.out.println(rs.getString(4));
	    	}
	    	
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
			System.out.println(System.currentTimeMillis() - start);
			start = System.currentTimeMillis();
	        rs = pstmt.executeQuery();
			System.out.println(System.currentTimeMillis() - start);
			start = System.currentTimeMillis();
			List<Test> testList = new ArrayList<Test>();
			while (rs.next()) {
				Test t = new Test();
				t.setProdId(rs.getLong(1));
				byte[] bb = rs.getBytes(2);
				byte[] bb2 = longToByte(rs.getLong(2));
				InputStream is = rs.getBinaryStream(2);
				byte[] bb3 = new byte[is.available()];
				is.read(bb3);
				System.out.println("bb:"+bb);
				t.setDate(new Date(rs.getLong(2)*1000));
				t.setPrice(rs.getLong(3));
				testList.add(t);
				
//				rs.getLong(1);
//				Date a = new Date(rs.getLong(2));
//				rs.getLong(3);
			}
			System.out.println("`````````````````````````````"+(System.currentTimeMillis() - start));
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	rs.close();
	    	pstmt.close();
	    	conn.close();
	    }
	}
	public static byte[] longToByte(long number) { 
        long temp = number; 
        byte[] b = new byte[8]; 
        for (int i = 0; i < b.length; i++) { 
            b[i] = new Long(temp & 0xff).byteValue();// 将最低位保存在最低位 
            temp = temp >> 8; // 向右移8位 
        } 
        return b; 
    } 
    

	public static void main(String[] args) throws SQLException, IOException {
		long start = System.currentTimeMillis();
		getAll();
		System.out.println(System.currentTimeMillis() - start);
	}
}
