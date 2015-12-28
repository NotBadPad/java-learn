package com.gj.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-28
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    public enum Color {RED,BLUE,GREEN};

    Color fruitColor() default Color.BLUE;
}
