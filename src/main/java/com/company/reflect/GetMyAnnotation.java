package com.company.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetMyAnnotation {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Person person = new Person("tzq", "123", "ltzq");
        doGetAnno(person);
        System.out.println(person);

    }

    private static void doGetAnno(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clz = obj.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(EnableAuth.class)) {
                EnableAuth annotation = field.getAnnotation(EnableAuth.class);
                if (annotation != null) {
                    String fieldName = field.getName();
                    // 获取set和get方法
                    Method setMehod = clz.getDeclaredMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), String.class);
                    String value = annotation.value();
                    setMehod.invoke(obj, value);
                }
            }
        }
    }
}
