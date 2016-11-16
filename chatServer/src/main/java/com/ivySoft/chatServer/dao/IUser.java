package com.ivySoft.chatServer.dao;

import java.util.List;

import com.ivySoft.chatServer.entity.User;

public interface IUser {
	/**
	 * 向USER表中插入一个用户
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 * 查询USER表中的所有用户
	 * 
	 * @return
	 */
	public List<User> findAllUser();

	/**
	 * 根据用户名和密码在USER表中查找用户信息
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public User findUserByParams(String userName, String password);

	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public User findUserByName(String userName);
	
	/**
	 * 查询指定用户名对应的Id
	 * @param userName
	 * @return
	 */
	public int findIdByName(String userName);
	
	/**
	 * 查询指定Id对应的用户名
	 * @param userName
	 * @return
	 */
	public String findNameById(int userId);
	
	/**
	 * 根据用户名获取用户好友列表
	 * 
	 * @param userName
	 * @return
	 */
	public String getFriendListByName(String userName);

	/**
	 * 添加好友
	 * 
	 * @param userName
	 * @param friendName
	 * @return
	 */
	public int addFriendByName(String userName, String friendName);

	/**
	 * 删除好友
	 * 
	 * @param userName
	 * @param friendName
	 * @return
	 */
	public int removeFriendByName(String userName, String friendName);

	/**
	 * 根据用户名获取用户状态
	 * 
	 * @param userName
	 * @return
	 */
	public String getUserStateByName(String userName);

	/**
	 * 修改用户状态
	 * 
	 * @param userName
	 * @param state
	 * @return
	 */
	public int modifyUserState(String userName, String state);
}
