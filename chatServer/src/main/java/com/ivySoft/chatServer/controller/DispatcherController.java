package com.ivySoft.chatServer.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import com.ivySoft.chatServer.service.UserService;
import com.ivySoft.chatServer.entity.Cluster;
import com.ivySoft.chatServer.service.ClusterService;
import com.ivySoft.chatServer.service.FriendContextService;
import com.ivySoft.chatServer.service.LoginService;
import com.ivySoft.chatServer.service.RegisterService;

/**
 * 服务器端调度器，将每一个客户端的Socket连接
 * 派发给一个内部线程DispacherThread处理
 * 
 * @author YangYong
 * 
 */
public class DispatcherController {
	private static DispatcherController instance;
	private static ReentrantLock lock = new ReentrantLock();

	private final ExecutorService pool;
	private final ServerSocket server;
	private final Map<String, Socket> LoginInfo;
	private final int poolSize = 2;
	private final int serverPort = 8080;
	private boolean isStop = false;

	public static DispatcherController getInstance() throws IOException {
		if (null == instance) {
			lock.lock();
			if (null == instance) {
				instance = new DispatcherController();
			}
			lock.unlock();
		}
		return instance;
	}

	private DispatcherController() throws IOException {
		// 初始化线程池
		pool = Executors.newFixedThreadPool(poolSize);
		// 初始化ServerSocket
		server = new ServerSocket(serverPort);
		server.setSoTimeout(30000);
		// 初始化记录用户登录信息的map
		LoginInfo = new HashMap<String, Socket>();
	}

	public void dispach() throws IOException {
		System.out.println("The server has started at localhost:" + serverPort);
		while (true) {
			if (isStop) {
				break;
			}
			try {
				DispacherThread task = new DispacherThread(server.accept());
				pool.submit(task);
			} catch (SocketTimeoutException e) {
				if (isStop) {
					return;
				}
			}

		}
		System.out.println("the server shut down");
	}

	// do something when stop the server
	public void destory() throws IOException {
		// never accept socket connection
		isStop = true;
		// release the thread pool
		pool.shutdown();

		// release the socket
		if (pool.isTerminated()) {
			// close all client socket
			Set<Entry<String, Socket>> socketSet = LoginInfo.entrySet();
			for (Entry<String, Socket> entry : socketSet) {
				entry.getValue().close();
			}
			// close server socket
			server.close();
		}
	}

	// internal thread class
	class DispacherThread implements Callable<Integer> {
		private final Socket socket;
		private final UserService userService;
		private final ClusterService clusterService;
		// 服务结果状态码
		private final int SUCCESS = 1;
		private final int FAIL = 0;
		// 定义服务类型状态码
		final int REGISTER = 0;
		final int LOGIN = 1;
		final int DISPLAYCONTEXT = 2;
		final int CALLFRIEND = 3;
		final int ONE2ONE = 4;
		final int MORE2MORE = 5;
		final int QUIT = 6;
		final int ALTERSTATE = 7;
		final int CREATECLUSTER = 8;
		
		private OutputStream out;
		private PrintStream ps;

		public DispacherThread(Socket socket) {
			this.socket = socket;
			// 初始化数据库服务
			this.userService = new UserService();
			clusterService = new ClusterService();
		}

		public Integer call() throws Exception {
			SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int serviceType = -1;
			// 获取服务类型状态码
			InputStream in;
			InputStreamReader read;
			BufferedReader br;
			in = socket.getInputStream();
			read = new InputStreamReader(in, "utf-8");
			br = new BufferedReader(read);
			serviceType = br.read();
			System.out.println("serviceType:" + serviceType);

			out = socket.getOutputStream();
			ps = new PrintStream(out, true, "utf-8");

			String userName;
			String password;
			String friendName;
			while (serviceType != QUIT) {
				// 根据服务类型，派发给相应的方法去处理
				switch (serviceType) {
				case REGISTER:
					userName = br.readLine();
					password = br.readLine();
					System.out.println(form.format(new Date()) + " 客户端：" + socket.getInetAddress().getHostAddress()
							+ ":" + socket.getPort() + " 请求注册");
					System.out.println("userName:" + userName + "  password:" + password);
					this.register(userName, password);
					break;
				case LOGIN:
					userName = br.readLine();
					password = br.readLine();
					System.out.println(form.format(new Date()) + " 客户端：" + socket.getInetAddress().getHostAddress()
							+ ":" + socket.getPort() + " 请求登录");
					System.out.println("userName:" + userName + "  password:" + password);
					this.login(userName, password);
					break;
				case DISPLAYCONTEXT:
					userName = br.readLine();
					System.out.println(form.format(new Date()) + " 客户端：" + socket.getInetAddress().getHostAddress()
							+ ":" + socket.getPort() + " 请求显示好友状态");
					System.out.println("userName:" + userName);
					this.displayfriendContext(userName);
					break;
				case CALLFRIEND:
					userName = br.readLine();
					friendName = br.readLine();
					System.out.println(form.format(new Date()) + " 客户端：" + socket.getInetAddress().getHostAddress()
							+ ":" + socket.getPort() + " 请求呼叫好友服务");
					System.out.println("userName:" + userName + " friendName:" + friendName);
					this.callFriend(userName, friendName);
					break;
				case ONE2ONE:
					String from = br.readLine();
					String to = br.readLine();
					String message = br.readLine();
					System.out.println(form.format(new Date()) + " 客户端：" + socket.getInetAddress().getHostAddress()
							+ ":" + socket.getPort() + " 请求one to one chat");
					System.out.println("from:" + from + " to:" + to + " message:" + message);
					this.sendMessageOne2One(from, to, message,0,false);
					break;
				case MORE2MORE:
					userName = br.readLine();
					int clusterId = br.read();
					message = br.readLine();
					System.out.println(form.format(new Date()) + " 客户端：" + socket.getInetAddress().getHostAddress()
							+ ":" + socket.getPort() + " 请求群聊"+
							" clusterId:"+clusterId+" message:"+message);
					this.clusterChat(userName, clusterId, message);
					break;
				case ALTERSTATE:
					userName = br.readLine();
					String state = br.readLine();
					System.out.println(form.format(new Date()) + " 客户端：" + socket.getInetAddress().getHostAddress()
							+ ":" + socket.getPort() + " 请求改变状态");
					System.out.println("state:" + state);
					this.alterState(userName, state);
					break;
				case CREATECLUSTER:
					String clusterName = br.readLine();
					userName = br.readLine();
					String memberList = br.readLine();
					System.out.println(form.format(new Date()) + " 客户端：" + socket.getInetAddress().getHostAddress() + ":"
							+ socket.getPort() + " 请求建群组");
					this.createCluster(clusterName, userName, memberList);
					break;
				default:
					break;
				}
				serviceType = br.read();
				System.out.println("serviceType:" + serviceType);
			}
			userName = br.readLine();
			System.out.println(form.format(new Date()) + " 客户端：" + socket.getInetAddress().getHostAddress() + ":"
					+ socket.getPort() + " 请求注销");
			System.out.println("userName:" + userName);
			this.quit(userName);
			// 正常退出
			return 0;
		}

		/**
		 * 用户注册
		 * 
		 * @param userName
		 * @param password
		 * @throws IOException
		 */
		private void register(String userName, String password) throws IOException {
			RegisterService registerService = new RegisterService(userName, password, userService);
			int count = registerService.regist();

			if (100 == count) {
				ps.println("user has existed");
			} else if (1 == count) {
				// 注册成功
				ps.println("success");
			} else {
				// 注册失败
				ps.println("fail");
			}
			ps.flush();
			out.flush();
		}

		/**
		 * 用户登录
		 * 
		 * @param userName
		 * @param password
		 * @throws IOException
		 */
		private void login(String userName, String password) throws IOException {
			LoginService loginService = new LoginService(userName, password, userService);

			if (null != loginService.login()) {
				// 登录成功
				int count = userService.modifyUserState(userName, "online");
				System.out.println("状态修改结果：" + count);
				String context = userService.getfriendContext(userName);
				System.out.println("好友列表：" + context);
				ps.println("success");
				if(null != context && context.length()>0){
					ps.write(SUCCESS);
					ps.println(context);
				}else{
					ps.write(FAIL);
				}
				// 返回群组列表
				List<Cluster> list = clusterService.getClusterByMemberName(userName);
				if(0==list.size()){
					ps.write(FAIL);
				}else{
					ps.write(SUCCESS);
					Cluster cluster;
					StringBuilder clusterList = new StringBuilder();
					StringBuilder memberList = new StringBuilder();
					int i=0;
					for(i=0;i<list.size()-1;i++){
						cluster = list.get(i);
						clusterList.append(cluster.getClusterName()+":"+cluster.getClusterId()+",");
						memberList.append(cluster.getClusterMember()+";");
					}
					cluster = list.get(i);
					clusterList.append(cluster.getClusterName()+":"+cluster.getClusterId());
					memberList.append(cluster.getClusterMember());
					ps.println(clusterList.toString());
					ps.println(memberList.toString());
				}
				// 保存用户的登录信息
				LoginInfo.put(userName, socket);
				System.out.println("当前在线用户数：" + LoginInfo.size());
			} else {
				// 登录失败
				ps.println("fail");
			}
			ps.flush();
			out.flush();
		}

		/**
		 * 实时显示好友状态
		 * 
		 * @param userName
		 * @throws IOException
		 */
		private void displayfriendContext(String userName) throws IOException {
			FriendContextService contextService = new FriendContextService(userName, userService);
			String context = contextService.getContext();

			ps.println(context);
			ps.flush();
			out.flush();
		}

		/**
		 * 呼叫好友
		 * 
		 * @param userName
		 * @param friendName
		 * @throws IOException
		 */
		private void callFriend(String userName, String friendName) throws IOException {
			Socket callSocket = LoginInfo.get(friendName);
			final int call = 2;
			if (null == callSocket) {
				// 对方未登录到服务器
				ps.write(FAIL);
			} else {
				OutputStream callOut = callSocket.getOutputStream();
				PrintStream callPS = new PrintStream(callOut, true, "utf-8");
				callPS.write(call);
				callPS.println(userName);
				callPS.flush();
				callOut.flush();
				ps.write(SUCCESS);
			}
			ps.flush();
			out.flush();
		}

		/**
		 * one to one chat
		 * 
		 * @param from
		 * @param to
		 * @param message
		 * @throws IOException
		 */
		private void sendMessageOne2One(String from, String to, String message,int groupId,boolean more) throws IOException {
			Socket toSocket = LoginInfo.get(to);
			final int One2One = 1;
			final int More2More = 4;
			
			// 对方未登录到服务器
			if (null == toSocket && !more) {
				// 返回0
				ps.write(FAIL);
			} else {
				OutputStream toOut;
				PrintStream toPS;
				toOut = toSocket.getOutputStream();
				toPS = new PrintStream(toOut, true, "utf-8");
				// 发状态码
				if(!more){
					toPS.write(One2One);
				}else{
					toPS.write(More2More);
				}
				if(0 == groupId){
					toPS.println(from);
					toPS.println(message);
				}else{
					toPS.write(groupId);
					toPS.println(from+":"+message);
				}
				toPS.flush();
				toOut.flush();
				// 返回1
				if(!more){
					ps.write(SUCCESS);
				}
			}
			ps.flush();
			out.flush();
		}

		/**
		 * 修改状态
		 * 
		 * @param userName
		 * @param state
		 */
		private void alterState(String userName, String state) {
			// 修改状态
			userService.modifyUserState(userName, state);
		}

		/**
		 * 创建群组
		 * @param clusterName
		 * @param owerName
		 * @param memberList
		 * @throws IOException 
		 */
		private void createCluster(String clusterName,String owerName,String memberList) throws IOException{
			int ownerId = userService.findIdByName(owerName);
			Cluster cluster = new Cluster();
			cluster.setClusterName(clusterName);
			cluster.setClusterOwner(ownerId);
			cluster.setClusterMember(owerName+","+memberList);
			SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			cluster.setCreateTime(fomat.format(new Date()));
			cluster.setUpdateTime(fomat.format(new Date()));
			int count = clusterService.insertCluster(cluster);
			int clusterId = 0;
			if(0 == count){
				ps.write(FAIL);
			}else{
				ps.write(SUCCESS);
				clusterId = clusterService.getClusterId(clusterName);
				ps.println(clusterName+":"+clusterId);
			}
			ps.flush();
			out.flush();
			// 通知群组里的在线用户
			final int notify = 3;
			String[] member = memberList.split(",");
			OutputStream toOut;
			PrintStream toPS;
			Socket socket;
			for(String userName:member){
				socket = LoginInfo.get(userName);
				if(null != socket){
					toOut = socket.getOutputStream();
					toPS = new PrintStream(toOut, true, "utf-8");
					toPS.write(notify);
					toPS.println(clusterName+":"+clusterId);
					toPS.println(memberList);
					toPS.flush();
					toOut.flush();
				}
			}
		}
		
		/**
		 * 群聊
		 * @throws IOException 
		 */
		private void clusterChat(String userName,int clusterId,String message) throws IOException{
			String memberList = clusterService.getMember(clusterId);
			String[] member = memberList.split(",");
			for(String temp:member){
				if(!temp.equals(userName)){
					this.sendMessageOne2One(userName, temp, message,clusterId,true);
				}
			}
		}
		
		/**
		 * 注销
		 * 
		 * @param userName
		 * @throws IOException
		 */
		private void quit(String userName) throws IOException {
			// 修改状态
			userService.modifyUserState(userName, "offline");
			// 从map中移除当前socket
			Socket socket = LoginInfo.remove(userName);
			System.out.println("当前在线用户数：" + LoginInfo.size());
			// 关闭socket连接
			if (!socket.isClosed()) {
				socket.close();
			}
		}
	}
}