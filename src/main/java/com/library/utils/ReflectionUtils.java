package com.library.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {

    public static void printFields(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                System.out.println(f.getName() + " = " + f.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}