package com.ivySoft.chatServer.servicetest;

import java.util.List;

import org.junit.Test;

import com.ivySoft.chatServer.entity.Cluster;
import com.ivySoft.chatServer.service.ClusterService;

public class ClusterServiceTest {
	private ClusterService service =  new ClusterService();
	
	@Test
	public void findAllClusterTest(){
		List<Cluster> list = service.findAllCluster();
		for(Cluster temp:list){
			System.out.println(temp.toString());
		}
	}
	
	@Test
	public void getClusterByMemberNameTest(){
		List<Cluster> list = service.getClusterByMemberName("Steve");
		for(Cluster temp:list){
			System.out.println(temp.toString());
		}
	}
	
	@Test
	public void getMemberTest(){
		String memberList = service.getMember(1);
		System.out.println(memberList);
	}
}
