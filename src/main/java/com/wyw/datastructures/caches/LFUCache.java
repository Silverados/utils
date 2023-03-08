package com.wyw.datastructures.caches;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Least Frequently Used
 * 频次优先
 * @param <K>
 * @param <V>
 */
public class LFUCache<K, V> {

    private final int capacity;
    private int minFrequency;
    private Map<K, Node<K, V>> keyMap;
    private Map<Integer, DoublyLinkedList<K, V>> frequencyMap;


    public LFUCache(int capacity) {
        this.minFrequency = 0;
        this.capacity = capacity;
        keyMap = new HashMap<>(capacity);
        frequencyMap = new HashMap<>(capacity);
    }

    public V get(K key) {
        if (capacity == 0) {
            return null;
        }

        if (!keyMap.containsKey(key)) {
            return null;
        }

        var node = keyMap.get(key);
        addFrequency(node);
        return node.value;
    }

    private void addFrequency(Node<K, V> node) {
        var freq = node.frequency;
        var frequencyDoublyLinkedList = frequencyMap.get(freq);
        frequencyDoublyLinkedList.remove(node);
        if (frequencyDoublyLinkedList.size == 0) {
            frequencyMap.remove(freq);
            if (minFrequency == freq) {
                minFrequency += 1;
            }
        }

        node.frequency++;
        var nextFrequencyList = frequencyMap.getOrDefault(node.frequency, new DoublyLinkedList<>());
        nextFrequencyList.addFirst(node);
        frequencyMap.put(node.frequency, nextFrequencyList);
    }

    public void put(K key, V value) {
        if (capacity == 0) {
            return;
        }

        if (!keyMap.containsKey(key)) {
            if (keyMap.size() == capacity) {
                var frequencyDoublyLinkedList = frequencyMap.get(minFrequency);
                var tail = frequencyDoublyLinkedList.getTail();
                keyMap.remove(tail.key);
                frequencyDoublyLinkedList.remove(tail);
                if (frequencyDoublyLinkedList.size == 0) {
                    frequencyMap.remove(minFrequency);
                }
            }

            var oneFrequencyList = frequencyMap.getOrDefault(1, new DoublyLinkedList<>());
            var node = new Node<>(key, value);
            oneFrequencyList.addFirst(node);
            frequencyMap.put(1, oneFrequencyList);
            keyMap.put(key, node);
            minFrequency = 1;
            return;
        }

        var node = keyMap.get(key);
        node.value = value;
        addFrequency(node);
    }

    public Set<K> keySet() {
        return keyMap.keySet();
    }


    private static class Node<K, V> {
        public K key;
        public V value;
        public int frequency;
        public Node<K, V> prev;
        public Node<K, V> next;

        public Node() {
            this(null, null, 0);
        }

        public Node(K k, V v) {
            this(k, v, 1);
        }

        public Node(K k, V v, int frequency) {
            key = k;
            value = v;
            this.frequency = frequency;
        }
    }

    private static class DoublyLinkedList<K, V> {
        private final Node<K, V> dummyHead;
        private final Node<K, V> dummyTail;
        private int size;

        DoublyLinkedList() {
            dummyHead = new Node<>();
            dummyTail = new Node<>();
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        public void addFirst(Node<K, V> node) {
            var prevHead = dummyHead.next;
            dummyHead.next = node;
            node.prev = dummyHead;
            node.next = prevHead;
            prevHead.prev = node;
            size++;
        }

        public void remove(Node<K, V> node) {
            var prev = node.prev;
            var next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }

        public Node<K, V> getHead() {
            return dummyHead.next;
        }

        public Node<K, V> getTail() {
            return dummyTail.prev;
        }

    }
}
