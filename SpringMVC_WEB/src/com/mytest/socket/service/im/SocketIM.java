package com.mytest.socket.service.im;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

@Service
public class SocketIM {
	
	public static ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
	
	/**
	 * 启动服务器
	 */
	public void socketStart(int port){
		System.out.println("服务端启动----------");
		try {
			ServerSocket server = new ServerSocket(port);
			//创建线程池
			ExecutorService myExecutorService = EXECUTOR_SERVICE;
			ServerThread ss = new ServerThread(server);
			myExecutorService.execute(ss);
			
			System.out.println("服务端启动，PORT："+port+"启动中... ...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
