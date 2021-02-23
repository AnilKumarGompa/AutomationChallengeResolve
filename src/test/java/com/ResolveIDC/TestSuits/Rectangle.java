package com.ResolveIDC.TestSuits;

public class Rectangle extends Shape{

    Rectangle(double width,double height){
        super(width,height);
        this.width = width;
        this.height=height;
    }

    public static double area(){
        return width*height;
    }
}
