package com.ivySoft.chatServer.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -2316524490553826879L;
	private int userId;
	private String userName;
	private String password;
	private String userState;
	private String friendList;
	private String createTime;
	private String updateTime;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getFriendList() {
		return friendList;
	}

	public void setFriendList(String friendList) {
		this.friendList = friendList;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userState="
				+ userState + ", friendList=" + friendList + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
}
