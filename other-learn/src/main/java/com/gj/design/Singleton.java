package com.gj.design;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-9
 * Time: 下午6:00
 * To change this template use File | Settings | File Templates.
 */
public class Singleton {

    private static Singleton singleton;

    private Singleton(){

    }

    public static Singleton getInstence(){
        if (singleton==null){
            synchronized (singleton){
                if (singleton==null){
                    singleton=new Singleton();
                }
            }
        }
        return singleton;
    }

}
