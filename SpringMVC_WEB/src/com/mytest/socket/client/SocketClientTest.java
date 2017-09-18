package com.mytest.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.mytest.socket.util.Util;

public class SocketClientTest {
	
	public static void acceptServer() throws IOException{
		System.out.println("客户端开始——————————————————————");
		//创建客户端Socket，指定服务器地址和端口
		Socket socket = new Socket(Util.IP, Util.PORT);
		socket.setSoTimeout(5000);
		
		//获取输出流，向服务器端发送信息
		//字节输出流
		OutputStream os = socket.getOutputStream();
		//将输出流包装为打印流
		PrintWriter pw = new PrintWriter(os);
		//客户端发送消息
		pw.write("客户端 " + Util.IP + " 接入服务器！");
		pw.write("验证消息000010000");
		pw.flush();
		//关闭输出流
		socket.shutdownOutput();
		
		//客户端等待服务端返回的消息
		//获取输入流
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String info;
		while ((info = br.readLine()) != null) {//循环读取客户端的信息
		    System.out.println("客户端接收服务端信息: " + info);
		}
		//socket.shutdownInput();//关闭输入流
		socket.close();
	}
	
	public static void main(String[] args) throws IOException {
		acceptServer();
	}

}
