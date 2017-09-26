package com.mytest.socket.client.im;

import java.io.BufferedReader;
import java.io.IOException;

public class SocketIMClient implements Runnable{
	private BufferedReader br;
	
	public SocketIMClient(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void run() {
		try {
			while(true){
				String info = br.readLine();
				System.out.println("收到服务器消息："+info);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
