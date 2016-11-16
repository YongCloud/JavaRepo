package com.ivySoft.chatServer.service;

import com.ivySoft.chatServer.dao.sqliteImpl.SQLiteUtil;

public class SQLiteService {
	private final String fileName;

	public SQLiteService() {
		// 数据库文件名
		fileName = "user.db";
	}

	public String getFileName() {
		return fileName;
	}

	/**
	 * 系统启动时将当前项目路径下建立一个user.db数据库文件， 并在该数据库中建立二张表,USER表用于存放用户信息，
	 * CLUSTER表用于存放群组信息
	 */
	public void initDataBase() {
		// 建立数据库
		SQLiteUtil SQLite = SQLiteUtil.getInstance();
		SQLite.createDB(fileName);
		// 建立user表
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE USER(").append("USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,")
				.append("USER_NAME VARCHAR(255) NOT NULL,").append("PASSWORD VARCHAR(255) NOT NULL,")
				.append("USER_STATE VARCHAR(6),").// 状态：在线、离开、忙碌
				append("FRIEND_LIST TEXT,").append("CREATE_TIME DATETIME NOT NULL,")
				.append("UPDATE_TIME DATETIME NOT NULL)");
		SQLite.createTable(fileName, sql.toString());
		// 建立cluster表
		StringBuilder sql2 = new StringBuilder();
		sql2.append("CREATE TABLE CLUSTER(").append("CLUSTER_ID INTEGER PRIMARY KEY AUTOINCREMENT,")
				.append("CLUSTER_NAME VARCHAR(255) NOT NULL,").append("CLUSTER_OWNER INTEGER NOT NULL,")
				.append("CLUSTER_MANAGER TEXT,").append("CLUSTER_MEMBER TEXT,").append("CREATE_TIME DATETIME NOT NULL,")
				.append("UPDATE_TIME DATETIME NOT NULL,")
				.append("FOREIGN KEY(CLUSTER_OWNER) REFERENCES USER(USER_ID))");
		SQLite.createTable(fileName, sql2.toString());
	}
}
