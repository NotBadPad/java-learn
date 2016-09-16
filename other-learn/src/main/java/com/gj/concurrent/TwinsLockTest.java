package com.gj.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created by guojing on 16/9/16.
 */
public class TwinsLockTest {


    public static void main(String[] args){
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run(){
                while (true){
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i=0; i<10; i++){
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("-----------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
