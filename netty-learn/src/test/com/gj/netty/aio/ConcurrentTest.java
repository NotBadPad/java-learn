package com.gj.netty.aio;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-11-20
 * Time: 上午9:24
 * To change this template use File | Settings | File Templates.
 */
public class ConcurrentTest {
    public static void main(String[] args) throws InterruptedException {
        Contents con = new Contents();
        Display dis = new Display(con);
        Sensor sen = new Sensor(con);
        //创建两个线程并调用start方法启动线程
        Thread sensorThread = new Thread(sen);
        Thread displayThread = new Thread(dis);
        sensorThread.start();
        displayThread.start();
    }
}
