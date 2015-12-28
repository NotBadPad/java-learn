package com.gj.annotation;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-12-28
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class Apple {
    @FruitName("apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String appleColor;

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public void printInfo() {
        System.out.print(appleName + " is " + appleColor);
    }

}
