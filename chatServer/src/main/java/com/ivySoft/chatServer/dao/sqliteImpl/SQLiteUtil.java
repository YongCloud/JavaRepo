package com.ivySoft.chatServer.dao.sqliteImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

public class SQLiteUtil {
	private static SQLiteUtil instance = null;
	private static ReentrantLock lock = new ReentrantLock();
	private String fileName = null;
	
	private SQLiteUtil(){
	}
	
	public static SQLiteUtil getInstance(){
		if(null == instance){
			lock.lock();
			if(null == instance){
				instance = new SQLiteUtil();
			}
			lock.unlock();
		}
		return instance;
	}
	
	public String getfileName() {
		return fileName;
	}

	public void setfileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 根据指定的文件名称，创建一个SQLite数据库
	 * @param fileName
	 */
	public void createDB(String fileName){
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			con.commit();
		} catch (ClassNotFoundException e) {
			System.err.println(e.toString());
		} catch (SQLException e) {
			System.err.println(e.toString());
		} finally{
			try{
				if(null != con){
					con.close();
				}
			}catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
	}
	
	/**
	 * 根据指定的数据库文件名称和SQL，创建表
	 * @param fileName
	 * @param sql
	 */
	public void createTable(String fileName,String sql){
		Connection con = null;
		PreparedStatement pre = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			pre = con.prepareStatement(sql);
			pre.execute();
			con.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if(null != pre){
					pre.close();
				}
				if(null != con){
					con.close();
				}
			}catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
	}
}
