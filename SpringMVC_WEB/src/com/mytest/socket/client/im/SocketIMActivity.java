package com.mytest.socket.client.im;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Service;

import com.mytest.socket.service.im.SocketIM;
import com.mytest.socket.util.Util;

@Service
public class SocketIMActivity{
    
	/**
	 * 启动客户端
	 */
	public void socketStart(int port){
		System.out.println("客户端启动----------");
		try {
			Socket socket = new Socket(Util.IP, Util.PORT);
			//创建线程池
			ExecutorService myExecutorService = SocketIM.EXECUTOR_SERVICE;
			ClientThread cs = new ClientThread(socket);
			myExecutorService.execute(cs);

			System.out.println("客户端启动，IP："+Util.IP+"启动中... ...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
