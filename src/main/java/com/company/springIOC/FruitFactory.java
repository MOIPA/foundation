package com.company.springIOC;

public class FruitFactory {
    public static IFruit getInstance(String className) {
        IFruit fruit = null;
        try {
            fruit = (IFruit) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fruit;
    }
}
