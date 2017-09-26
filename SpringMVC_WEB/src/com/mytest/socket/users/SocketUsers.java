package com.mytest.socket.users;

public class SocketUsers {
	
	public SocketUsers(String Name,String IP,Integer PORT) {
		this.IP = IP;
		this.Name = Name;
		this.PORT = PORT;
	}

	private Integer PORT;
	private String IP;
	private String Name;
	
	public Integer getPORT() {
		return PORT;
	}
	public void setPORT(Integer pORT) {
		PORT = pORT;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

}
