package com.ivySoft.chatServer.servicetest;

import org.junit.Test;

import com.ivySoft.chatServer.service.SQLiteService;

public class SQLiteServiceTest {
	private SQLiteService service = new SQLiteService();
	
	@Test
	public void initDataBaseTest() {
		service.initDataBase();
	}
}
