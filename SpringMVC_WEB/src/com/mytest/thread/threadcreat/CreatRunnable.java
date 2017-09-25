package com.mytest.thread.threadcreat;

public class CreatRunnable implements Runnable{

	public void run() {
		System.out.println("------------Runnable start------------");
		
		for (int i = 0; i < 20; i++) {
			System.out.println("B -----" + i);
		}
		
		System.out.println("------------ Runnable end ------------");
	}

}
