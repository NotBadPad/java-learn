package com.gj.netty.aio;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-11-20
 * Time: 上午9:27
 * To change this template use File | Settings | File Templates.
 */
public class Contents {

    private double temperature;
    private double hunmidness;
    private double wind_speed;
    private boolean flag = true;
    //同步函数
    public synchronized void setContent(double temperature,double hunmidness,double wind_speed) {
        System.out.println("setContent:"+flag);
        if(!flag)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.temperature = temperature;
        this.hunmidness = hunmidness;
        this.wind_speed = wind_speed;
        flag = false;
        notify();
//        else{
//            this.temperature = temperature;
//            this.hunmidness = hunmidness;
//            this.wind_speed = wind_speed;
//            flag = false;
//        }
//        notify();
    }
    public synchronized void getContent() {
        System.out.println("getContent:"+flag);
        if(flag)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("--"+temperature+"---"+hunmidness+"---"+wind_speed);
        System.out.println("-------------------------------------------------");
        flag = true;
        notify();
//        else{
//            System.out.println("--"+temperature+"---"+hunmidness+"---"+wind_speed);
//            System.out.println("-------------------------------------------------");
//            flag = true;
//        }
//        notify();
    }
}
