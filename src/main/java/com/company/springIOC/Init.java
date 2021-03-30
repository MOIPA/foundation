package com.company.springIOC;

import java.io.*;
import java.util.Properties;

public class Init {
    public static Properties getPro() throws FileNotFoundException, IOException {
        Properties pro=new Properties();
        File f=new File("fruit.properties");
        if(f.exists()){
            pro.load(new FileInputStream(f));
        }else{
            pro.setProperty("apple", "springIOC.Apple");
            pro.setProperty("orange", "springIOC.Orange");
            pro.store(new FileOutputStream(f), "FRUIT CLASS");
        }
        return pro;
    }
}
