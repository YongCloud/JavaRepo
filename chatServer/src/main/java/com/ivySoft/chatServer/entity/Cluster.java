package com.ivySoft.chatServer.entity;

import java.io.Serializable;

public class Cluster implements Serializable {
	private static final long serialVersionUID = -5691972280690741181L;
	private int clusterId;
	private String clusterName;
	private int clusterOwner;
	private String clusterManager;
	private String clusterMember;
	private String createTime;
	private String updateTime;

	public int getClusterId() {
		return clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public int getClusterOwner() {
		return clusterOwner;
	}

	public void setClusterOwner(int clusterOwner) {
		this.clusterOwner = clusterOwner;
	}

	public String getClusterManager() {
		return clusterManager;
	}

	public void setClusterManager(String clusterManager) {
		this.clusterManager = clusterManager;
	}

	public String getClusterMember() {
		return clusterMember;
	}

	public void setClusterMember(String clusterMember) {
		this.clusterMember = clusterMember;
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
		return "Cluster [clusterId=" + clusterId + ", clusterName=" + clusterName + ", clusterOwner=" + clusterOwner
				+ ", clusterManager=" + clusterManager + ", clusterMember=" + clusterMember + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
}
