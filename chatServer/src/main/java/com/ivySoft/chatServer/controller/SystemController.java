package com.ivySoft.chatServer.controller;

import java.io.IOException;

/**
 * 聊天服务器系统入口
 * 
 * @author YangYong
 *
 */
public class SystemController {

	public static void main(String[] args) {
		DispatcherController instance;
		try {
			instance = DispatcherController.getInstance();
			// instance.destory();
			instance.dispach();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
