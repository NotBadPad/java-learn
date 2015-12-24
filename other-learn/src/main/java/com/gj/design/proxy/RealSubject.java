package com.gj.design.proxy;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-24
 * Time: 下午6:06
 * To change this template use File | Settings | File Templates.
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("real subject");
    }
}
