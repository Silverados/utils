package com.wyw.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    @Test
    public void test() {
        // 创建两个Set
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        // 向set1和set2添加相同的元素
        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(3);
        set2.add(2);
        set2.add(1);


        System.out.println(set1.equals(set2));
        System.out.println(set1 == set2);
    }
}
