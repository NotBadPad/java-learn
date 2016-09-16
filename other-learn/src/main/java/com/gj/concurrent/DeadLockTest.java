package com.gj.concurrent;

/**
 * 死锁测试
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-8
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
public class DeadLockTest implements Runnable {
    public static Object o1 = new Object();
    public static Object o2 = new Object();

    public int flag=0;
    public DeadLockTest(int flag){
        this.flag=flag;
    }

    public void run() {
        if (flag==0){
            synchronized (DeadLockTest.o1){
                System.out.println(Thread.currentThread().getName()+":wait release o2 lock");
                synchronized (DeadLockTest.o2)  {
                    System.out.println(Thread.currentThread().getName()+":get o2 lock");
                }
            }
        } else{
            synchronized (DeadLockTest.o2){
                System.out.println(Thread.currentThread().getName()+":wait release o1 lock");
                synchronized (DeadLockTest.o1)  {
                    System.out.println(Thread.currentThread().getName()+":get o1 lock");
                }
            }
        }
    }

    public static void main(String[] args){
        DeadLockTest dt1 =new DeadLockTest(0);
        DeadLockTest dt2 = new DeadLockTest(1);
        Thread t1 = new Thread(dt1);
        t1.setName("t1");
        Thread t2 = new Thread(dt2);
        t2.setName("t2");

        t1.start();
        t2.start();
    }
}
