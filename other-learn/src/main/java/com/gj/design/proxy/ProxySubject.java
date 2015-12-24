package com.gj.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-24
 * Time: 下午6:09
 * To change this template use File | Settings | File Templates.
 */
public class ProxySubject implements InvocationHandler {
    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("handler before");
        method.invoke(subject,args);
        System.out.println("handler after");
        return null;
    }

    public static void main(String[] args){
        RealSubject rs = new RealSubject();
        InvocationHandler handler = new ProxySubject(rs);
        Subject subject1 = (Subject)Proxy.newProxyInstance(rs.getClass().getClassLoader(),rs.getClass().getInterfaces(),handler);
        subject1.request();
    }
}
