package com.mytest.socket.service.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端的线程
 * @author Administrator
 *
 */
public class ServerThread implements Runnable{
	
	private ServerSocket serverSocket;
	
	public ServerThread(ServerSocket serverSocket) throws IOException{
		this.serverSocket = serverSocket;
	}
	
	@Override
	public void run() {
		try {
			while(true){
				Socket socket = serverSocket.accept();
				//接收客户端信息
				String info = getSocketInputStream(socket);
				System.out.println(info);
				getSocketOutputStream(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收客户端信息
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	public String getSocketInputStream(Socket socket) throws IOException{
		//获取输入流
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br  = new BufferedReader(isr);
		
		//循环读取客户端信息
		String info;
		while (null != (info = br.readLine())) {
			System.out.println("服务端接收到的客户端信息："+info);
		}
		return info;
	}
	
	/**
	 * 接连成功反馈信息
	 * @param socket
	 * @throws IOException
	 */
	public void getSocketOutputStream(Socket socket) throws IOException{
		//服务端收到客户端消息后返回一条消息
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.write("服务端口："+socket.getPort()+"  连接成功！");
		pw.flush();
	}
}
