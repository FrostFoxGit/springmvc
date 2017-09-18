package com.mytest.socket.service.im;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mytest.socket.util.Util;

public class SocketIM_T {
	
    private List<Socket> mList = new ArrayList<Socket>();
    private ServerSocket server = null;
    private ExecutorService myExecutorService = null;
	
	public SocketIM_T(){
		try {
			System.out.println("服务端启动----------");
			server = new ServerSocket(Util.PORT);
			//创建线程池
			myExecutorService = Executors.newCachedThreadPool();
			System.out.println("服务端启动，IP："+Util.IP+"启动中... ...");
			Socket client = null;
			while (true) {
                client = server.accept();
                mList.add(client);
                SocketService socketService = new SocketService(client);
                myExecutorService.execute(socketService);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	class SocketService implements Runnable{
		
		private Socket socket;
		private BufferedReader in = null;
		private String msg = "";
		
		SocketService(Socket socket){
	        this.socket = socket;
	        try {
	        	InputStreamReader isr = new InputStreamReader(socket.getInputStream());
	            in = new BufferedReader(isr);
	            msg = "用户:" + this.socket.getInetAddress() + "--加入了聊天室" + 
	            		"当前在线人数:" + mList.size();
	            this.sendMsg();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}

		@Override
		public void run(){
			try {
				while(true){
					if(null != (msg = in.readLine())){
						if("bye".equals(msg)){
							System.out.println("--------退出--------");
							mList.remove(socket);
							in.close();
							msg = "用户:" + socket.getInetAddress()
                                    + "退出:" + "当前在线人数:" + mList.size();
							socket.close();
							this.sendMsg();
							break;
						}else{
                            msg = socket.getInetAddress() + "   说: " + msg;
                            this.sendMsg();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//为连接上服务端的每个客户端上发送信息
		public void sendMsg(){
			System.out.println(msg);
			int num = mList.size();
            for (int index = 0; index < num; index++) {
                Socket mSocket = mList.get(index);
                PrintWriter pw = null;
                try {
                	OutputStreamWriter osw = new OutputStreamWriter(mSocket.getOutputStream(), "UTF-8");
                	BufferedWriter bw = new BufferedWriter(osw);
                    pw = new PrintWriter(bw, true);
                    pw.println(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
	}
}
