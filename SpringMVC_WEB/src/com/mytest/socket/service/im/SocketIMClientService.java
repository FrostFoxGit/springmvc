package com.mytest.socket.service.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.mytest.socket.users.SocketUsers;

public class SocketIMClientService implements Runnable{
	
	private SocketUsers socketUsers;
/*	private Socket socket;*/
	private BufferedReader br;
	private PrintWriter pw;
	private List<SocketIMClientService> simcList;
	
	public SocketIMClientService(Socket socket,List<SocketIMClientService> simcList) {
/*		this.socket = socket;*/
		this.simcList = simcList;
		
		try {
			//服务器接收消息
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			br = new BufferedReader(isr);
			//服务器反馈消息
			OutputStream os = socket.getOutputStream();
			pw = new PrintWriter(os);
			
			String info;
			while(null != (info = br.readLine())){
				System.out.println("----- 服务器收到客户端信息：");
				System.out.println("----- "+info);
			}
			@SuppressWarnings("null")
			String[] userString = info.split("#");
/*			SocketUsers su = new SocketUsers(userString[0],userString[1],Integer.parseInt(userString[2]));*/
			
			pw.write("----- 用户:"+userString[0]+"  登录成功-----");
			pw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		while(true){
			try {
				String info = null;
				info = br.readLine();
				
				String[] userString = info.split("#");
				
				for (int i = 0; i < simcList.size(); i++) {
					pw.write("----- 用户:"+userString[0]+"  登录成功-----");
					pw.flush();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public SocketUsers getSocketUsers() {
		return socketUsers;
	}

	public BufferedReader getBr() {
		return br;
	}

	public PrintWriter getPw() {
		return pw;
	}

}
