package com.mytest.thread.threadcreat;

public class RunMainThread {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		CreatRunnable cr = new CreatRunnable();
		CreatThread ct = new CreatThread();
		Thread t = new Thread(cr);
		
		ct.start();
		t.start();
		System.out.println(ct.currentThread().getId());
		System.out.println(ct.getThreadGroup());
		System.out.println(ct.isAlive());
		System.out.println(ct.isInterrupted());
		
		System.out.println(t.currentThread().getId());
		System.out.println(t.currentThread().getName());
	}
	
}
