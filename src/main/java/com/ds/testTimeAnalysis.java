package com.ds;

public class testTimeAnalysis {
    public static void main(String[] args) {
        int x=91;
        int y=100;
        int count=0;
        while (y > 0) {
            count++;
            if (x > 100) {
                System.out.println("count"+count+" x :"+x+" y:"+y);
                x-=10;
                y--;
            } else x++;
        }
        System.out.println(count);
    }
}
