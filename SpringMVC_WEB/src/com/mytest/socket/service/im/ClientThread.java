package com.mytest.socket.service.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.mytest.socket.util.Util;

/**
 * 客户端线程
 * @author Administrator
 *
 */
public class ClientThread implements Runnable{
	
    private Socket socket;
    private List<ClientThread> clients;
    private BufferedReader br;  
    private PrintWriter pw;
    
    public PrintWriter getPW(){
    	return pw;
    }
    
    public BufferedReader getBR(){
    	return br;
    }
    
	public ClientThread(Socket socket,List<ClientThread> clients) throws IOException{
		this.socket = socket;
		this.clients = clients;
		//输出流
		OutputStream os = socket.getOutputStream();
		this.pw = new PrintWriter(os);
		//输出流
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		this.br  = new BufferedReader(isr);
		
		//接收用户的基本信息
		String info;
		while (null != (info = br.readLine())) {
			System.out.println("服务端接收到的客户端信息："+info);
		}
		//反馈服务器成功信息
		pw.println("服务器返回客户端信息："+Util.IP+"连接成功！");
		System.out.println("服务器返回客户端信息："+Util.IP+"连接成功！");
		pw.flush();
		//向所有用户反馈信息
		for (int i = 0; i < clients.size(); i++) {
			clients.get(i).getPW().println("用户:"+Util.IP+"  已上线！");
			clients.get(i).getPW().flush();
		}
	}

	@Override
	public void run() {
		String info = null;
		try {
			while(true){
				info = br.readLine();
				if(info.equals("CLOSE")){
					br.close();
					pw.close();
					socket.close();
					
					for (int i = 0; i < clients.size(); i++) {
						clients.get(i).getPW().println("用户下线");
						clients.get(i).getPW().flush();
					}
				}else{
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
