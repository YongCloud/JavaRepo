package com.ivySoft.chatServer.dao.sqliteImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.ivySoft.chatServer.dao.ICluster;
import com.ivySoft.chatServer.entity.Cluster;

public class ClusterMapper implements ICluster {
	private final String fileName;
	
	public ClusterMapper(String fileName) {
		this.fileName = fileName;
	}

	public String getfileName() {
		return fileName;
	}
	
	public int insertCluster(Cluster cluster) {
		Connection con = this.getConnection();
		
		PreparedStatement pre = null;
		int count = 0;
		// 同步锁
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		
		try {
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO CLUSTER(CLUSTER_NAME,CLUSTER_OWNER,CLUSTER_MEMBER,CREATE_TIME,UPDATE_TIME) ").
			append("VALUES(?,?,?,?,?)");
			pre = con.prepareStatement(sql.toString());
			pre.setString(1,cluster.getClusterName());
			pre.setInt(2,cluster.getClusterOwner());
			pre.setString(3,cluster.getClusterMember());
			pre.setString(4,cluster.getCreateTime().toString());
			pre.setString(5,cluster.getUpdateTime().toString());
			count =  pre.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(lock.isLocked()){
				lock.unlock();
			}
			this.closeConnection(con, pre, null);
		}
		return count;
	}

	public int deleteCluster(String clusterName) {
		Connection con = this.getConnection();
		
		PreparedStatement pre = null;
		int count = 0;
		// 同步锁
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		
		try {
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM CLUSTER ").
			append("WHERE CLUSTER_NAME=?");
			pre = con.prepareStatement(sql.toString());
			pre.setString(1,clusterName);
			count =  pre.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(lock.isLocked()){
				lock.unlock();
			}
			this.closeConnection(con, pre, null);
		}
		return count;
	}
	
	public String getMember(int clusterId) {
		Connection con = this.getConnection();
		
		PreparedStatement pre = null;
		ResultSet set = null;
		String member = null;
		try {
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CLUSTER_MEMBER FROM CLUSTER ").
			append("WHERE CLUSTER_ID=?");
			pre = con.prepareStatement(sql.toString());
			pre.setInt(1,clusterId);
			set =  pre.executeQuery();
			con.commit();
			while(set.next()){
				member = set.getString("CLUSTER_MEMBER");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeConnection(con, pre, set);
		}
		return member;
	}

	public int getOwner(String clusterName) {
		Connection con = this.getConnection();
		
		PreparedStatement pre = null;
		ResultSet set = null;
		int owner = 0;
		try {
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CLUSTER_OWNER FROM CLUSTER ").
			append("WHERE CLUSTER_NAME=?");
			pre = con.prepareStatement(sql.toString());
			pre.setString(1,clusterName);
			set =  pre.executeQuery();
			con.commit();
			while(set.next()){
				owner = set.getInt("CLUSTER_OWNER");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeConnection(con, pre, set);
		}
		return owner;
	}

	public int addMember(String clusterName, String userName) {
		return 0;
	}

	public int removeMember(String clusterName, String userName) {
		return 0;
	}

	public int addManager(String clusterName, String userName) {
		return 0;
	}

	public int removeManager(String clusterName, String userName) {
		return 0;
	}

	public int getClusterId(String clusterName) {
		Connection con = this.getConnection();
		
		PreparedStatement pre = null;
		ResultSet set = null;
		int id = 0;
		try {
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CLUSTER_ID FROM CLUSTER ").
			append("WHERE CLUSTER_NAME=?");
			pre = con.prepareStatement(sql.toString());
			pre.setString(1,clusterName);
			set =  pre.executeQuery();
			con.commit();
			while(set.next()){
				id = set.getInt("CLUSTER_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeConnection(con, pre, set);
		}
		return id;
	}

	public List<Cluster> getClusterByMemberName(String memberName) {
		Connection con = this.getConnection();
		
		PreparedStatement pre = null;
		ResultSet set = null;
		List<Cluster> list = new ArrayList<Cluster>();
		try {
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM CLUSTER");
//			append("WHERE CLUSTER_MEMBER LIKE %").
//			append("'"+memberName+"'").append("%");
			pre = con.prepareStatement(sql.toString());
			set =  pre.executeQuery();
			con.commit();
			String memberList;
			String[] temp;
			while(set.next()){
				memberList = set.getString("CLUSTER_MEMBER");
				temp = memberList.split(",");
				for(String t:temp){
					if(t.equals(memberName)){
						Cluster cluster = new Cluster();
						cluster.setClusterId(set.getInt("CLUSTER_ID"));
						cluster.setClusterName(set.getString("CLUSTER_NAME"));
						cluster.setClusterManager(set.getString("CLUSTER_MANAGER"));
						cluster.setClusterOwner(set.getInt("CLUSTER_OWNER"));
						cluster.setClusterMember(set.getString("CLUSTER_MEMBER"));
						cluster.setCreateTime(set.getString("CREATE_TIME"));
						cluster.setUpdateTime(set.getString("UPDATE_TIME"));
						list.add(cluster);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeConnection(con, pre, set);
		}
		return list;
	}

	public List<Cluster> findAllCluster() {
		Connection con = this.getConnection();
		
		PreparedStatement pre = null;
		ResultSet set = null;
		List<Cluster> list = new ArrayList<Cluster>();
		try {
			con.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM CLUSTER ");
			pre = con.prepareStatement(sql.toString());
			set = pre.executeQuery();
			con.commit();
			while (set.next()) {
				Cluster cluster = new Cluster();
				cluster.setClusterId(set.getInt("CLUSTER_ID"));
				cluster.setClusterName(set.getString("CLUSTER_NAME"));
				cluster.setClusterManager(set.getString("CLUSTER_MANAGER"));
				cluster.setClusterOwner(set.getInt("CLUSTER_OWNER"));
				cluster.setClusterMember(set.getString("CLUSTER_MEMBER"));
				cluster.setCreateTime(set.getString("CREATE_TIME"));
				cluster.setUpdateTime(set.getString("UPDATE_TIME"));
				list.add(cluster);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(con, pre, set);
		}
		return list;
	}
	
	private Connection getConnection(){
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+fileName;
			con = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void closeConnection(Connection con, PreparedStatement pre,ResultSet set) {
		try {
			if (null != set) {
				set.close();
			}
			if (null != pre) {
				pre.close();
			}
			if (null != con) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != set) {
					set.close();
				}
				if (null != pre) {
					pre.close();
				}
				if (null != con) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
