package com.ivySoft.chatServer.dao;

import java.util.List;

import com.ivySoft.chatServer.entity.Cluster;

public interface ICluster {
	/**
	 * 向CLUSTER表中插入一个cluster
	 * 
	 * @param cluster
	 * @return
	 */
	public int insertCluster(Cluster cluster);
	
	/**
	 * 查询指定群名对应的Id
	 * @param clusterName
	 * @return
	 */
	public int getClusterId(String clusterName);
	
	/**
	 * 删除群
	 * @param clusterName
	 * @return
	 */
	public int deleteCluster(String clusterName);
	
	/**
	 * 获取给定群的成员
	 * 
	 * @param clusterName
	 * @return
	 */
	public String getMember(int clusterId);

	/**
	 * 获取给定群的群主
	 * 
	 * @param clusterName
	 * @return
	 */
	public int getOwner(String clusterName);

	/**
	 * 查询用户所在的群
	 * @param memberName
	 * @return
	 */
	public List<Cluster> getClusterByMemberName(String memberName);
	
	/**
	 * 查询CLSTER表中的所有群组信息
	 * @return
	 */
	public List<Cluster> findAllCluster();
	
	/**
	 * 为给定群添加成员
	 * 
	 * @param clusterName
	 * @param userName
	 * @return
	 */
	public int addMember(String clusterName, String userName);

	/**
	 * 将给定用户移出指定的群
	 * 
	 * @param clusterName
	 * @param userName
	 * @return
	 */
	public int removeMember(String clusterName, String userName);

	/**
	 * 添加管理员
	 * 
	 * @param clusterName
	 * @param userName
	 * @return
	 */
	public int addManager(String clusterName, String userName);

	/**
	 * 删除管理员
	 * 
	 * @param clusterName
	 * @param userName
	 * @return
	 */
	public int removeManager(String clusterName, String userName);
}
