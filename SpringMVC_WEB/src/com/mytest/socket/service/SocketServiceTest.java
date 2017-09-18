package com.mytest.socket.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.mytest.socket.util.Util;

public class SocketServiceTest {

	@SuppressWarnings("resource")
	public static void SocketTest() throws IOException{
		ServerSocket serverSocket = new ServerSocket(Util.PORT);
		
		System.out.println("服务端就绪，等待客户接入————————");
		System.out.println("服务端IP："+Util.IP+" 端口号："+Util.PORT);
		
		//调用accept()等待客户端连接,建立连接后，accept()返回一个最近创建的Socket对象
		//该Socket对象绑定了客户程序的IP地址或端口号
		Socket socket = serverSocket.accept();
		//获取输入流
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br  = new BufferedReader(isr);
		
		//循环读取客户端信息
		String info;
		while (null != (info = br.readLine())) {
			System.out.println("服务端接收到的客户端信息："+info);
		}
		
		//关闭输入流
		socket.shutdownInput();
		
		//服务端收到客户端消息后返回一条消息
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.write("服务端"+Util.IP+"返回消息！");
		pw.write("服务验证0010000100");
		pw.flush();
		System.out.println("服务端返回信息给客户端：服务端"+Util.IP+"返回消息！");
		//socket.shutdownOutput();
		socket.close();
	}
	
	public static void main(String[] args) throws IOException {
		SocketTest();
	}

}
