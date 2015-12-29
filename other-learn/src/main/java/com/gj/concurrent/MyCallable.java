package com.gj.concurrent;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-29
 * Time: 下午3:39
 * To change this template use File | Settings | File Templates.
 */
public class MyCallable implements Callable {
    private int flag = 0;

    public MyCallable(int flag) {
        this.flag = flag;
    }

    @Override
    public Integer call() throws Exception {
        if (flag == 0) {
            while (true){
                System.out.println(Thread.currentThread().getName()+":runing");
                Thread.sleep(1000);
            }
        } else {
            System.out.println(Thread.currentThread().getName()+":end");
            return 1;
        }
    }
}
