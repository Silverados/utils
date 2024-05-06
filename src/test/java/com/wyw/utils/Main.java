package com.wyw.utils;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static List<Long> list = new ArrayList<>();
    @Test
    public void test() {
        list.add(1L);
        list.add(2L);
        list.add(3L);

        for (Iterator<Long> iterator = list.iterator(); iterator.hasNext(); ) {
            Long l = iterator.next();
            if (l == 2) {
                continue;
            }
            remove(l, iterator);
        }
    }

    private void remove(Long l, Iterator<Long> iterator) {
        if (iterator == null) {
            list.remove(l);
        } else {
            iterator.remove();
        }
    }
}
