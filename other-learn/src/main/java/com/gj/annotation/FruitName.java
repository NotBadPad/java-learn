package com.gj.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-28
 * Time: 下午3:39
 * To change this template use File | Settings | File Templates.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "none";
}
