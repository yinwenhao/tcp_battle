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

import com.mysql.jdbc.StringUtils;

import when_how.hero.dao.entity.Test;

public class MybatisTest {
	private static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		
		String url = "jdbc:mysql://10.128.8.36:58111/lang_resource_info?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useUnicode=true";
		String username = "rw_user";
		String password = "Z9a9dy3BcIMnrbf2Xfdp";
		
//		String url = "jdbc:mysql://127.0.0.1:3306/hero?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
//		String username = "root";
//		String password = "123456";
		
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
	    String sql = "SELECT   prod_id,   UNIX_TIMESTAMP(date),   price FROM   t_rs_product_calendar_price WHERE   prod_id IN ( 1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 ,10 ,11 ,12 ,13 ,14 ,15 ,16 ,17 ,18 ,19 ,20 ,21 ,22 ,23 ,24 ,25 ,26 ,27 ,28 ,29 ,30 ,31 ,32 ,33 ,34 ,35 ,36 ,37 ,38 ,39 ,40 ,41 ,42 ,43 ,44 ,45 ,46 ,47 ,48 ,49 ,50 ,51 ,52 ,53 ,54 ,55 ,56 ,57 ,58 ,59 ,60 ,61 ,62 ,63 ,64 ,65 ,66 ,67 ,68 ,69 ,70 ,71 ,72 ,73 ,74 ,75 ,76 ,77 ,78   ) AND date >= '2016-06-10' AND date <= '2016-09-07';";
//	    String sql = "SELECT   prod_id,   UNIX_TIMESTAMP(date),   price FROM   t_rs_product_calendar_price WHERE   prod_id = 1 AND date = '2016-06-10' ";
//	    String sql = "SELECT   id,a,b,c FROM   test WHERE   id = 1";
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
//	    	Statement s = conn.createStatement();
//	    	rs = s.executeQuery(sql);
//	    	if (rs.next()) {
//	    		System.out.println(rs.getString(1));
//	    		System.out.println(rs.getString(2));
//	    		System.out.println(rs.getString(3));
//	    		System.out.println(rs.getString(4));
//	    	}
	    	
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
			System.out.println(System.currentTimeMillis() - start);
			start = System.currentTimeMillis();
	        rs = pstmt.executeQuery();
			System.out.println(System.currentTimeMillis() - start);
			start = System.currentTimeMillis();
			List<Test> testList = new ArrayList<Test>();
			while (rs.next()) {
				Test t = new Test();
//				t.setProdId(getLongFromStringBytes(rs.getBytes(1)));
//				t.setDate(new Date(getLongFromStringBytes(rs.getBytes(2))*1000));
//				t.setPrice(getLongFromStringBytes(rs.getBytes(3)));

				t.setProdId(rs.getLong(1));
				t.setDate(new Date(rs.getLong(2)*1000));
				t.setPrice(rs.getLong(3));
				
				testList.add(t);
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
	
	public static long byteToLong(byte[] b)

	  {
	    long l = 0;

	    l = b[0];

	    l |= ((long) b[1] << 8);

	    l |= ((long) b[2] << 16);

	    l |= ((long) b[3] << 24);

	    l |= ((long) b[4] << 32);

	    l |= ((long) b[5] << 40);

	    l |= ((long) b[6] << 48);

	    l |= ((long) b[7] << 56);

	    return l;
	  }
	
	public static long getLongFromStringBytes(byte[] b) {
        long result = 0;
        
        for (byte bb : b) {
        	char c = (char) bb;
        	c -= '0';
        	result *= 10;
        	
        	result += c;
        }
        return result;
	}
    

	public static void main(String[] args) throws SQLException, IOException {
		long start = System.currentTimeMillis();
		getAll();
		System.out.println(System.currentTimeMillis() - start);
//		byte[] abc = "12345".getBytes();
//		start = System.currentTimeMillis();
//		for (int i=0; i<1000000; i++) {
////			System.out.println(getFromStringBytes(abc));
//			getLongFromStringBytes(abc);
//		}
//		System.out.println(System.currentTimeMillis() - start);
////		start = System.currentTimeMillis();
//		long bbb = 12345;
//		byte[] bcd = longToByte(bbb);
//		start = System.currentTimeMillis();
//		for (int i=0; i<1000000; i++) {
////			System.out.println(byteToLong(bcd));
//			byteToLong(bcd);
//		}
//		System.out.println(System.currentTimeMillis() - start);
	}
	
	
}
