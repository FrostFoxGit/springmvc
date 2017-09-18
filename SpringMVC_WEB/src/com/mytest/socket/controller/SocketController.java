package com.mytest.socket.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mytest.socket.client.im.SocketIMActivity;
import com.mytest.socket.service.im.SocketIM;

@Controller
public class SocketController {
	
	@Resource
	private SocketIM socketIM;
	
	private SocketIMActivity socketIMActivity;
	
	/**
	 * 创建通讯服务器
	 */
	@RequestMapping("/serviceSocketIM")
	public void serviceSocketIM(){
		socketIM.socketStart(5233);
	}
	
	/**
	 * 创建通讯客户端
	 */
	@RequestMapping("/clientSocketIM")
	public void clientSocketIM(){
		socketIMActivity.socketStart(5233);
	}
	
}
