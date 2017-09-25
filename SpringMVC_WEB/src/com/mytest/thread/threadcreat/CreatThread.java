package com.mytest.thread.threadcreat;

public class CreatThread extends Thread{
	
	public void run(){
		System.out.println("------------Thread start------------");
		
		for (int i = 0; i < 20; i++) {
			System.out.println("A -----" + i);
		}
		
		System.out.println("------------ Thread end ------------");
	}

}
