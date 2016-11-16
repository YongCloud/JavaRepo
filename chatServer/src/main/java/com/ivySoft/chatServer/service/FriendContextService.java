package com.ivySoft.chatServer.service;

public class FriendContextService {
	private String userName = null;
	private UserService userService = null;

	public FriendContextService(String userName, UserService userService) {
		this.userName = userName;
		this.userService = userService;
	}

	public String getContext() {
		String context = userService.getfriendContext(userName);
		return context;
	}
}
