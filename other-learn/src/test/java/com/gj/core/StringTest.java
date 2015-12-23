package com.gj.core;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-22
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
public class StringTest {

    @Test
    public void testIntegerHashCode(){
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        System.out.println(i1.hashCode());
        System.out.println(i2.hashCode());

    }
}
