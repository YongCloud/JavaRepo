package com.ivySoft.chatServer.dao.sqliteImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.ivySoft.chatServer.dao.IUser;
import com.ivySoft.chatServer.entity.User;

public class UserMapper implements IUser {
	private final String fileName;
	
	public UserMapper(String fileName){
		this.fileName = fileName;
	}
	
	public String getfileName() {
		return fileName;
	}

	public int insertUser(User user) {
		Connection con = null;
		PreparedStatement pre = null;
		int count = 0;
		// 同步锁
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO USER(USER_NAME,PASSWORD,USER_STATE,CREATE_TIME,UPDATE_TIME) ").
			append("VALUES(?,?,?,?,?)");
			pre = con.prepareStatement(sql.toString());
			pre.setString(1,user.getUserName());
			pre.setString(2,user.getPassword());
			pre.setString(3,user.getUserState());
			pre.setString(4,user.getCreateTime());
			pre.setString(5,user.getUpdateTime());
			count =  pre.executeUpdate();
			con.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(lock.isLocked()){
				lock.unlock();
			}
			try{
				if(null != pre){
					pre.close();
				}
				if(null != con){
					con.close();
				}
			}catch (SQLException e) {
				System.err.println(e.toString());
				lock.unlock();
			}
		}
		return count;
	}

	public List<User> findAllUser() {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet set = null;
		List<User> list = new ArrayList<User>();
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ").
			append("FROM USER");
			pre = con.prepareStatement(sql.toString());
			set = pre.executeQuery();
			con.commit();
			while(set.next()){
				User user = new User();
				user.setUserId(set.getInt("USER_ID"));
				user.setUserName(set.getString("USER_NAME"));
				user.setPassword(set.getString("PASSWORD"));
				user.setUserState(set.getString("USER_STATE"));
				user.setFriendList(set.getString("FRIEND_LIST"));
				user.setCreateTime(set.getString("CREATE_TIME"));
				user.setUpdateTime(set.getString("UPDATE_TIME"));
				list.add(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if(null != set){
					set.close();
				}
				if(null != pre){
					pre.close();
				}
				if(null != con){
					con.close();
				}
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
		return list;
	}

	public User findUserByParams(String userName, String password) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet set = null;
		User user = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ").
			append("FROM USER ").
			append("WHERE USER_NAME=? AND PASSWORD=?");
			pre = con.prepareStatement(sql.toString());
			pre.setString(1,userName);
			pre.setString(2,password);
			set = pre.executeQuery();
			con.commit();
			while(set.next()){
				user = new User();
				user.setUserId(set.getInt("USER_ID"));
				user.setUserName(set.getString("USER_NAME"));
				user.setPassword(set.getString("PASSWORD"));
				user.setUserState(set.getString("USER_STATE"));
				user.setFriendList(set.getString("FRIEND_LIST"));
				user.setCreateTime(set.getString("CREATE_TIME"));
				user.setUpdateTime(set.getString("UPDATE_TIME"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if(null != set){
					set.close();
				}
				if(null != pre){
					pre.close();
				}
				if(null != con){
					con.close();
				}
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
		return user;
	}

	public User findUserByName(String userName) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet set = null;
		User user = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ").
			append("FROM USER ").
			append("WHERE USER_NAME=?");
			pre = con.prepareStatement(sql.toString());
			pre.setString(1,userName);
			set = pre.executeQuery();
			con.commit();
			while(set.next()){
				user = new User();
				user.setUserId(set.getInt("USER_ID"));
				user.setUserName(set.getString("USER_NAME"));
				user.setPassword(set.getString("PASSWORD"));
				user.setUserState(set.getString("USER_STATE"));
				user.setFriendList(set.getString("FRIEND_LIST"));
				user.setCreateTime(set.getString("CREATE_TIME"));
				user.setUpdateTime(set.getString("UPDATE_TIME"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if(null != set){
					set.close();
				}
				if(null != pre){
					pre.close();
				}
				if(null != con){
					con.close();
				}
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
		return user;
	}

	public String getFriendListByName(String userName) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet set = null;
		String friendList = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT FRIEND_LIST ").
			append("FROM USER ").
			append("WHERE USER_NAME=?");
			pre = con.prepareStatement(sql.toString());
			pre.setString(1,userName);
			set = pre.executeQuery();
			con.commit();
			while(set.next()){
				friendList = set.getString("FRIEND_LIST");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if(null != set){
					set.close();
				}
				if(null != pre){
					pre.close();
				}
				if(null != con){
					con.close();
				}
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
		return friendList;
	}

	public int addFriendByName(String userName,String friendName) {
		String friendList = this.getFriendListByName(userName);
		if(null == friendList){
			friendList = "";
		}else{
			String[] temp = friendList.split(",");
			for(String friend:temp){
				if(friend.equals(friendName)){
					System.out.println("该用户已是你好友，不能重复添加...");
					return 0;
				}
			}
		}
		Connection con = null;
		// 使用PreparedStatement有问题！！！
		Statement stmt = null;
		int count = 0;
		
		// 同步锁
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			stmt = con.createStatement();
			if(friendList.equals("")){
				friendList += friendName;
			}else{
				friendList += ","+friendName;
			}
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE USER ").
			append("SET FRIEND_LIST='").
			append(friendList+"' ").
			append("WHERE USER_NAME='").
			append(userName+"'");
			count = stmt.executeUpdate(sql.toString());
			con.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(lock.isLocked()){
				lock.unlock();
			}
			try{
				if(null != stmt){
					stmt.close();
				}
				if(null !=con){
					con.close();
				}
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
		return count;
	}

	public String getUserStateByName(String userName) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet set = null;
		String userState = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT USER_STATE ").
			append("FROM USER ").
			append("WHERE USER_NAME=?");
			pre = con.prepareStatement(sql.toString());
			pre.setString(1,userName);
			set = pre.executeQuery();
			con.commit();
			while(set.next()){
				userState = set.getString("USER_STATE");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if(null != set){
					set.close();
				}
				if(null != pre){
					pre.close();
				}
				if(null != con){
					con.close();
				}
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
		return userState;
	}

	public int modifyUserState(String userName, String state) {
		int count = 0;
		Connection con = null;
		PreparedStatement pre = null;
		// 同步锁
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE USER ").
			append("SET USER_STATE=? ").
			append("WHERE USER_NAME=?");
			pre = con.prepareStatement(sql.toString());
			pre.setString(1,state);
			pre.setString(2,userName);
			count = pre.executeUpdate();
			con.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(lock.isLocked()){
				lock.unlock();
			}
			try{
				if(null != pre){
					pre.close();
				}
				if(null != con){
					con.close();
				}
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
		return count;
	}

	public int removeFriendByName(String userName, String friendName) {
		return 0;
	}

	public int findIdByName(String userName) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet set = null;
		int userId = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT USER_ID ").
			append("FROM USER ").
			append("WHERE USER_NAME=?");
			pre = con.prepareStatement(sql.toString());
			pre.setString(1,userName);
			set = pre.executeQuery();
			con.commit();
			while(set.next()){
				userId = set.getInt("USER_ID");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if(null != set){
					set.close();
				}
				if(null != pre){
					pre.close();
				}
				if(null != con){
					con.close();
				}
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
		return userId;
	}

	public String findNameById(int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet set = null;
		String userName = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT USER_NAME ").
			append("FROM USER ").
			append("WHERE USER_ID=?");
			pre = con.prepareStatement(sql.toString());
			pre.setInt(1, userId);
			set = pre.executeQuery();
			con.commit();
			while(set.next()){
				userName = set.getString("USER_NAME");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if(null != set){
					set.close();
				}
				if(null != pre){
					pre.close();
				}
				if(null != con){
					con.close();
				}
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
		return userName;
	}
}
