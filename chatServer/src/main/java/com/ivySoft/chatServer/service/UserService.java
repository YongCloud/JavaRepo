package com.ivySoft.chatServer.service;

import java.util.List;

import com.ivySoft.chatServer.dao.IUser;
import com.ivySoft.chatServer.dao.sqliteImpl.UserMapper;
import com.ivySoft.chatServer.entity.User;

public class UserService {
	private final String fileName;
	private IUser userMapper = null;

	public UserService() {
		fileName = new SQLiteService().getFileName();
		userMapper = new UserMapper(fileName);
	}

	public int insertUser(User user) {
		return userMapper.insertUser(user);
	}

	public List<User> findAllUser() {
		return userMapper.findAllUser();
	}

	public User findUserByParams(String userName, String password) {
		return userMapper.findUserByParams(userName, password);
	}

	public User findUserByName(String userName) {
		return userMapper.findUserByName(userName);
	}

	public int findIdByName(String userName){
		return userMapper.findIdByName(userName);
	}
	
	public String findNameById(int userId){
		return userMapper.findNameById(userId);
	}
	
	public String getFriendListByName(String userName) {
		return userMapper.getFriendListByName(userName);
	}

	public int addFriendByName(String userName, String friendName) {
		return userMapper.addFriendByName(userName, friendName);
	}

	public String getUserStateByName(String userName) {
		return userMapper.getUserStateByName(userName);
	}

	/**
	 * 根据用户名获取该用户的好友列表和各个好友的状态
	 * 
	 * @param userName
	 * @return
	 */
	public String getfriendContext(String userName) {
		String friendList = this.getFriendListByName(userName);
		String[] friend = friendList.split(",");
		StringBuilder context = new StringBuilder();
		int i = 0;
		for (i = 0; i < friend.length - 1; i++) {
			context.append(friend[i] + ":");
			context.append(this.getUserStateByName(friend[i]));
			context.append(",");
		}
		context.append(friend[i] + ":");
		context.append(this.getUserStateByName(friend[i]));
		return context.toString();
	}

	public int modifyUserState(String userName, String state) {
		return userMapper.modifyUserState(userName, state);
	}
}
