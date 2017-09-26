package com.mytest.socket.client.im;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.mytest.socket.util.Util;

/**
 * 客户端线程
 * @author Administrator
 *
 */
public class ClientThread implements Runnable{
	
	private Socket socket;
    
	public ClientThread(Socket socket) throws IOException{
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("123000");
			//获取输出流，向服务器端发送信息
			//字节输出流
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			//客户端发送消息
			pw.write("客户端 " + Util.IP + " 接入服务器！");
			pw.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
