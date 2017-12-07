package com.kapphk.web.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class JDBCUtil {

	private static String driver = "com.mysql.jdbc.Driver" ;
	
	private static Properties prop = null ;
	
	static{
		try {
			Class.forName(driver) ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws Exception{
		prop = new Properties() ;
		InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties") ;
		prop.load(in) ;
		
		String url = (String)prop.get("jdbc.url") ;
		String user = (String)prop.get("jdbc.username") ;
		String password = (String)prop.get("jdbc.password") ;
		return DriverManager.getConnection(url, user, password) ;
	}
	
	public static void release(ResultSet rs ,PreparedStatement stmt,Connection conn){
		if(rs != null){
			try {
				rs.close() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null ;
			}
		}
		if(stmt != null){
			try {
				stmt.close() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				stmt = null ;
			}
		}
		if(conn != null){
			try {
				conn.close() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null ;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getConnection());
	}
}
