package com.gj.design.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-24
 * Time: 下午6:53
 * To change this template use File | Settings | File Templates.
 */
public class ProxySubjectCglib implements MethodInterceptor {
    private Subject subject;

    public Object getInstance(Subject subject) {
        this.subject = subject;
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(this.subject.getClass());
        enhancer.setCallback(this);
        return  enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("handler before");
        methodProxy.invokeSuper(o, objects);
        System.out.println("handler after");
        return null;
    }

    public static void main(String[] args){
        ProxySubjectCglib psc = new ProxySubjectCglib();
        RealSubject rs =(RealSubject)psc.getInstance(new RealSubject());
        rs.request();
    }
}
