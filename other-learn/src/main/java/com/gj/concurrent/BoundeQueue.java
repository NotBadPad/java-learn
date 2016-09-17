package com.gj.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by guojing on 16/9/17.
 */
public class BoundeQueue<T> {
    private Object[] items;
    private int addIndex,removeIndex,count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundeQueue(int size) {
        items = new Object[size];
    }

    public void add(T t) throws InterruptedException {
        try {
            lock.lock();
            while (count == items.length){
                notFull.await();
            }
            items[addIndex] = t;
            if (++addIndex == items.length){
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        try {
            lock.lock();
            while (count == 0){
                notEmpty.await();
            }

            Object x = items[removeIndex];
            if (++removeIndex ==items.length){
                removeIndex =0;
            }
            --count;
            notFull.signal();
            return (T)x;
        } finally {
            lock.unlock();
        }
    }
}
