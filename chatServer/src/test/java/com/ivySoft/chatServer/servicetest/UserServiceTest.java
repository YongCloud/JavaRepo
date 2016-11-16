package com.ivySoft.chatServer.servicetest;

import java.util.List;

import org.junit.Test;

import com.ivySoft.chatServer.entity.User;
import com.ivySoft.chatServer.service.UserService;

public class UserServiceTest {
	private UserService service = new UserService();
	
	@Test
	public void findAllUserTest(){
		List<User> list = service.findAllUser();
		for(User user:list){
			System.out.println(user);
		}
	}
	
	@Test
	public void getFriendListByNameTest(){
		String userName = "Tom";
		String friendList =  service.getFriendListByName(userName);
		System.out.println(friendList);
	}
	
	@Test
	public void findUserByParamsTest(){
		String userName = "Tom";
		String password = "123456";
		User user = service.findUserByParams(userName, password);
		if(null != user){
			System.out.println("登录成功！");
		}else{
			System.out.println("登录失败");
		}
	}
	
	@Test
	public void addFriendByNameTest(){
		// Tom  Steve  Valley  Mary  Jenny	test
		String userName = "test";
		String friendName = "Mary";
		int count =  service.addFriendByName(userName, friendName);
		System.out.println(count);
	}
	
	@Test
	public void getUserStateByNameTest(){
		String userName = "Tom";
		String userState = service.getUserStateByName(userName);
		System.out.println(userState);
	}
	
	@Test
	public void getfriendContextTest(){
		String userName = "Tom";
		String context = service.getfriendContext(userName);
		System.out.println(context);
	}
	
	@Test
	public void modifyUserStateTest(){
//		String userName = "Tom";
//		String state = "offline";
//		int count = service.modifyUserState(userName, state);
//		System.out.println(count);
	}
	
	@Test
	public void findNameByIdTest(){
		int userId = 1;
		System.out.println(service.findNameById(userId));
	}
	
	@Test
	public void findIdByNameTest(){
		System.out.println(service.findIdByName("Tom"));
	}
}
