package com.gj.netty.aio;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-11-20
 * Time: 上午9:27
 * To change this template use File | Settings | File Templates.
 */
public class Sensor implements Runnable {
    //传感器对象，用来随机生成Content对象的值
    private Contents con;
    private int count;
    public Sensor(Contents con) {
        // TODO Auto-generated constructor stub
        this.con=con;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(count<10)
        {
            if(count%2!=0){
                con.setContent(20+(Math.random()*20), Math.random(), Math.random()*20);
            }
            else{
                con.setContent(50, 2, 30);
            }
            count++;
        }
    }
}
