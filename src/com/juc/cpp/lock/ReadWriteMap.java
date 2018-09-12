package com.juc.cpp.lock;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 线程安全的Map
 * @param <K>
 * @param <V>
 */
public class ReadWriteMap<K,V> {
    private final Map<K,V> map;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();

    public ReadWriteMap(Map<K,V> map) {
        this.map = map;
    }

    public V put(K key,V value) {
        w.lock();
        try{
            return map.put(key, value);
        }finally {
            w.unlock();
        }
    }

    public V get(K key) {
        r.lock();
        try{
            return map.get(key);
        }finally {
            r.unlock();
        }
    }
}
