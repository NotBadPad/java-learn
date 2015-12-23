package com.gj.core;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-23
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public enum EnumValueTest {
    RED(255,0,0),BLUE(0,0,255),BLACK(0,0,0),YELLOW(255,255,0),GREEN(0,255,0);

    private int redValue;  //自定义数据域，private为了封装。
    private int greenValue;
    private int blueValue;

    private EnumValueTest(int rv,int gv,int bv){
        this.redValue=rv;
        this.greenValue=gv;
        this.blueValue=bv;
    }
}
