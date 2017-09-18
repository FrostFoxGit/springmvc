package com.mytest.socket.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;
public class Util {
	
	public static int PORT = Util.getProperties("SocketTool.PORT","socketTool");
	public static String IP = Util.getIP();

	/**
	 * 获取Properties参数值
	 * @param key		需要取值的键
	 * @param urlName	Properties名称
	 * @return
	 */
	public static int getProperties(String key,String urlName){
		Properties prop = new Properties();
		File file = null;
		if("socketTool".equals(urlName)){
			file = new File("E:/MY_Item/Git/springmvc_web/SpringMVC_WEB/WebContent/WEB-INF/configure/socketTool.properties");
		}
		try {
			//装载配置文件
			prop.load(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//返回获取的值
		return Integer.parseInt(prop.getProperty(key));
	}
	
	/**
	 * 获取本地IP
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getIP(){
		String IP = null;
		try {
			Enumeration en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements())
			{
				NetworkInterface ni = (NetworkInterface) en.nextElement();
				if (ni.isVirtual() || "virbr0".equals(ni.getName())) {
					continue;
				}
				
	            Enumeration<InetAddress> ee = ni.getInetAddresses();
	            while (ee.hasMoreElements()) {
	                InetAddress ia = ee.nextElement();
	                if (!ia.isLoopbackAddress() && !(ia instanceof Inet6Address)) {
	                    IP = ia.getHostAddress();
	                    break;
	                }
	            }
	            if(null!=IP){
	            	break;
	            }
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return IP;
	}
	
	public static void main(String[] args) {
		System.out.println(Util.getProperties("SocketTool.PORT","socketTool"));
		System.out.println(PORT);
	}
}
