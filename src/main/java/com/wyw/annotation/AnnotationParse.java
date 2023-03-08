package com.wyw.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParse {
    public static final Class<RequestMapping> requestMapping = RequestMapping.class;
    public static void main(String[] args) {
        var clazz = RootController.class;
        var clazzAnnotation = clazz.getAnnotation(requestMapping);
        if (clazzAnnotation != null) {
            System.out.println("Class name: " + clazz.getName() + ", Annotation value: " + clazzAnnotation.value());
        }

        for (Method method : clazz.getDeclaredMethods()) {
            var methodAnnotation = method.getAnnotation(requestMapping);
            if (methodAnnotation == null) {
                continue;
            }
            System.out.println("Method name: " + method.getName() + ", Annotation value: " + methodAnnotation.value());
        }

    }
}
