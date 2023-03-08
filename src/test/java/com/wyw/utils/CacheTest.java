package com.wyw.utils;

import com.wyw.datastructures.caches.LFUCache;
import com.wyw.datastructures.caches.LRUCache;
import org.junit.jupiter.api.Test;

public class CacheTest {

    @Test
    public void LRUCacheTest() {
        var cache = new LRUCache<Integer, Integer>(2);
        cache.put(1, 1);
        cache.put(2, 1);
        cache.put(2, 1);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(3, 1);

        System.out.println(cache.keySet());
    }

    @Test
    public void LFUCacheTest() {
        var cache = new LFUCache<>(2);
        cache.put(1, 1);
        cache.put(2, 1);
        cache.put(2, 1);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(3, 1);

        System.out.println(cache.keySet());

    }
}
