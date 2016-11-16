package com.ivySoft.chatServer.service;

import java.util.List;

import com.ivySoft.chatServer.dao.ICluster;
import com.ivySoft.chatServer.dao.sqliteImpl.ClusterMapper;
import com.ivySoft.chatServer.entity.Cluster;

public class ClusterService {
	private final String fileName;
	private ICluster clusterMapper;

	public ClusterService() {
		this.fileName = new SQLiteService().getFileName();
		this.clusterMapper = new ClusterMapper(fileName);
	}

	public int insertCluster(Cluster cluster) {
		return clusterMapper.insertCluster(cluster);
	}

	public int getClusterId(String clusterName){
		return clusterMapper.getClusterId(clusterName);
	}
	
	public String getMember(int clusterId) {
		return clusterMapper.getMember(clusterId);
	}

	public int getOwner(String clusterName) {
		return clusterMapper.getOwner(clusterName);
	}

	public List<Cluster> getClusterByMemberName(String memberName){
		return clusterMapper.getClusterByMemberName(memberName);
	}
	
	
	public List<Cluster> findAllCluster(){
		return clusterMapper.findAllCluster();
	}
	
	public int addMember(String clusterName, String userName) {
		return clusterMapper.addMember(clusterName, userName);
	}

	public int removeMember(String clusterName, String userName) {
		return clusterMapper.removeMember(clusterName, userName);
	}

	public int addManager(String clusterName, String userName) {
		return clusterMapper.addManager(clusterName, userName);
	}

	public int removeManager(String clusterName, String userName) {
		return clusterMapper.removeManager(clusterName, userName);
	}
}
