package com.mytest.socket.service.im;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.mytest.socket.util.Util;

public class IMService{
	
	private Integer PORT;
	private ExecutorService EXECUTOR_SERVICE;
	private SocketIMService ss;
	private List<SocketIMClientService> simcList;
	
	
	IMService(Integer PORT){
		this.PORT = PORT;
		this.EXECUTOR_SERVICE = Util.EXECUTOR_SERVICE;
	}
	
	public void startSocketIM(){
		try {
			System.out.println("-----PORT:"+PORT+" 服务启动");
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.err.println("-----服务启动成功");
			simcList = new ArrayList<SocketIMClientService>();
			ss = new SocketIMService(serverSocket,simcList);
			EXECUTOR_SERVICE.execute(ss);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
