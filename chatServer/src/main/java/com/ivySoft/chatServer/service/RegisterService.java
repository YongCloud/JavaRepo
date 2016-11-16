package com.ivySoft.chatServer.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ivySoft.chatServer.entity.User;

public class RegisterService {
	private String userName = null;
	private String password = null;
	private UserService userService = null;

	public RegisterService(String userName, String password, UserService userService) {
		this.userName = userName;
		this.password = password;
		this.userService = userService;
	}

	public int regist() {
		User user;
		user = userService.findUserByName(userName);
		final int USER_EXIST = 100;
		if (null != user) {
			return USER_EXIST;
		}

		user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setUserState("offline");
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setCreateTime(form.format(new Date()));
		user.setUpdateTime(form.format(new Date()));
		return userService.insertUser(user);
	}
}