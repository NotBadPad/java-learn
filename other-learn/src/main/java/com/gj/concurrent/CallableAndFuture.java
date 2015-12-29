package com.gj.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-29
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
public class CallableAndFuture {
    public static void main(String[] args) {
        MyCallable mc1 = new MyCallable(0);
        MyCallable mc2 = new MyCallable(1);

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future f1 =  es.submit(mc1);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        f1.cancel(true);

        Future f2 = es.submit(mc2);
        try {
            System.out.println(f2.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
