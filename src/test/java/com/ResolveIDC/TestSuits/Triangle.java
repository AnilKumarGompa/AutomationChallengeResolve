package com.ResolveIDC.TestSuits;

public class Triangle extends Shape{
    Triangle(double width,double height){
        super(width,height);
        this.width = width;
        this.height=height;
    }

    public static double area(){
        return (width*height)/2;
    }
}
