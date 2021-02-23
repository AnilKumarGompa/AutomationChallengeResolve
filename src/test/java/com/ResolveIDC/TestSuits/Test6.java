package com.ResolveIDC.TestSuits;

public class Test6 {
    public static void main(String[] args){
        double width = 15, height = 20;
        Rectangle rectangle = new Rectangle(width,height);
        Triangle triangle = new Triangle(width,height);

        double rectangleArea = rectangle.area();
        double triangleArea = triangle.area();
        System.out.println("rectangleArea: "+rectangleArea);
        System.out.println("triangleArea: "+triangleArea);
    }
}
