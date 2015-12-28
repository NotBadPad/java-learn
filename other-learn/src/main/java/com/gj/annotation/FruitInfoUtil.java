package com.gj.annotation;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-28
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
public class FruitInfoUtil {
    public static void getFruitInfo(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();

        for (Field field:fields){
            if(field.isAnnotationPresent(FruitName.class)){//获取fruitName信息
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                System.out.println("name:"+fruitName.value());
            }
        }
    }

    public static void main(String[] args){
        FruitInfoUtil.getFruitInfo(Apple.class);
    }
}
