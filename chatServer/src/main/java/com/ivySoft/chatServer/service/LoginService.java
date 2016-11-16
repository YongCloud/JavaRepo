package com.ivySoft.chatServer.service;

import com.ivySoft.chatServer.entity.User;

public class LoginService {
	private String userName = null;
	private String password = null;
	private UserService userService = null;

	public LoginService(String userName, String password, UserService userService) {
		this.userName = userName;
		this.password = password;
		this.userService = userService;
	}

	public User login() {
		User user = userService.findUserByParams(userName, password);
		return user;
	}
}
