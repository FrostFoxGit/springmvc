package com.mytest.main;

import java.util.Vector;//输入java矢量

class SyncStack {// 实现堆栈功能，不能同时读写
	@SuppressWarnings("rawtypes")
	// 私人接口向量缓冲区			// 新建向量
	private Vector buffer = new Vector(400, 200);
	char contents;
	private boolean ava = false;

	public synchronized char get() {// 出栈
		// 如果生产者还没有产生字符就一直等待
		while (ava == false) {
			try {
				this.wait();
				// 当线程在活动之前或活动期间处于正在等待、休眠或占用状态且该线程被中断时，抛出该异常
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		ava = false;
		notifyAll();
		return contents;
	}

	@SuppressWarnings("unchecked")
	public synchronized void push(char c) {
		while (ava == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}// 入栈
		ava = true;
		this.notify();// 通知其它线程把数据出栈
		Character charObj = new Character(c);
		buffer.addElement(charObj);
		contents = c;
	}

	public void print(char c) {

	}
}

class ControlEnter {// 每行输出结果的个数
	public static int count = 0;
	void counter() {
		count++;
		if (count == 5) {
			System.out.println("----------------");
			count = 0;
		}
	}
}

class Producer implements Runnable {// 生产者类
	private SyncStack theStack;// 生产者类获得的字符都来自同步堆栈
	private ControlEnter controlenter;

	public Producer(SyncStack s, ControlEnter ce) {
		theStack = s;
		controlenter = ce;
	}

	public void run() {
		char c;
		for (int i = 0; i < 30; i++) {
			c = (char) (Math.random() * 26 + 'a');// 随机产生30个小写字母
			theStack.push(c);// 存入堆栈
			System.out.print("生产者产生: " + c + "    ");
			controlenter.counter();
			try {
				Thread.sleep// 线程休眠
				((int) (Math.random() * 200));// 以0~200ms的速度随机
			// 当线程在活动之前或活动期间处于正在等待、休眠或占用状态且该线程被中断时，抛出该异常
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {// 消费者类
	private SyncStack theStack;// 消费者类获得的字符都来自同步堆栈
	private ControlEnter controlenter;

	public Consumer(SyncStack s, ControlEnter ce) {
		theStack = s;
		controlenter = ce;
	}

	public void run() {
		char value;
		for (int i = 0; i < 30; i++) {// 从堆栈中取数，并输出
			value = theStack.get();// 取出生产者产生的字母
			System.out.print("消费者消费: " + value + "    ");// 消费者输出
			controlenter.counter();

			try {
				Thread.sleep((int)
				(Math.random() * 2000));// 控制取数的速度：0～2s/*每读取一个字符线程就睡眠*/
			// 当线程在活动之前或活动期间处于正在等待、休眠或占用状态且该线程被中断时，抛出该异常
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}

class B {// 主线程总调度
	public static void main(String[] args) {
		ControlEnter ce = new ControlEnter();
		SyncStack stack = new SyncStack();// 下面的消费者类对象和生产者类对象所操作的是同一个同步堆栈对象
		Producer p1 = new Producer(stack, ce);
		new Thread(p1).start();// 生产者线程启动
		Consumer c1 = new Consumer(stack, ce);
		new Thread(c1).start();// 消费者线程启动
	}
}
