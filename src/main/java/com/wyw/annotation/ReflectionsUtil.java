package com.wyw.annotation;

import org.reflections.Reflections;

import java.util.Set;

public class ReflectionsUtil {
    public static void main(String[] args) {
        var reflection = new Reflections("com.wyw");
        Set<Class<?>> classes = reflection.getTypesAnnotatedWith(RequestMapping.class);
        for (Class<?> clazz : classes) {
            RequestMapping clazzAnnotation = clazz.getAnnotation(RequestMapping.class);
            System.out.println("Class name: " + clazz.getName() + ", Annotation value: " + clazzAnnotation.value());
        }

    }
}
