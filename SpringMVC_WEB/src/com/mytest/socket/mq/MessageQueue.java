package com.mytest.socket.mq;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.aspectj.bridge.Message;

public class MessageQueue {
    Message[] mItems;
    int mPutIndex;
    //队列中消息数
    private int mCount;
    private int mTakeIndex;
    //锁
    Lock mLock;
    //条件变量
    Condition mNotEmpty;//可取
    Condition mNotFull;//可添加

    public MessageQueue() {
        mItems = new Message[50];
        mLock = new ReentrantLock();
        mNotEmpty = mLock.newCondition();
        mNotFull = mLock.newCondition();
    }
	
    Message next() {
        Message msg = null;
        try {
            mLock.lock();
            //检查队列是否空了
            while (mCount <= 0) {
                //阻塞
                mNotEmpty.await();
                System.out.println("队列空了，读锁阻塞");
            }
            msg = mItems[mTakeIndex];//可能空
            //消息被处理后，置空数组中该项
            mItems[mTakeIndex] = null;
            //处理越界，index大于数组容量时，取第一个item
            mTakeIndex = (++mTakeIndex >= mItems.length) ? 0 : mTakeIndex;
            mCount--;
            //通知生产者生产
            mNotFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
        return msg;
    }
	
    public void enqueueMessage(Message message) {
    }

}
