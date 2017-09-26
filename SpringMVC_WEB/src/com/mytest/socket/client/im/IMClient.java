package com.mytest.socket.client.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.mytest.socket.util.Util;

public class IMClient {
	
	private PrintWriter pw;
	private BufferedReader br;
	
	public void connectServer(String name,String IP,Integer PORT){
		try {
			Socket socket = new Socket(Util.IP, Util.PORT);
			//客户端接收消息
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			br = new BufferedReader(isr);
			//客户端反馈消息
			OutputStream os = socket.getOutputStream();
			pw = new PrintWriter(os);
			
			SocketIMClient simc = new SocketIMClient(br);
			Thread t = new Thread(simc);
			t.start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String info) {
		pw.println(info);
		pw.flush();
	}
	
}
