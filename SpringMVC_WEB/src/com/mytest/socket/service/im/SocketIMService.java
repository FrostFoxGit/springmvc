package com.mytest.socket.service.im;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class SocketIMService implements Runnable{
	
	private ServerSocket serverSocket;
	private List<SocketIMClientService> simcList;
	private Socket socket;
	
	public SocketIMService(ServerSocket serverSocket,List<SocketIMClientService> simcList){
		this.serverSocket = serverSocket;
		this.simcList = simcList;
	}

	@Override
	public void run() {
		while(true){
			try {
				socket = serverSocket.accept();
				SocketIMClientService simcs = new SocketIMClientService(socket, simcList);
				Thread t = new Thread(simcs);
				t.start();
				simcList.add(simcs);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void socketIMLogin(){
		
	}
	
	

}
