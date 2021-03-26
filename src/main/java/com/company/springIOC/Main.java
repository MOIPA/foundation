package com.company.springIOC;

import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties = Init.getPro();
        IFruit fruit = FruitFactory.getInstance(properties.getProperty("apple"));
        fruit.eat();
    }
}
