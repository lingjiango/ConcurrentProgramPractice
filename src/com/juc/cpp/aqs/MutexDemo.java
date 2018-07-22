package com.juc.cpp.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class MutexDemo {
//  private static Mutex mutex = new Mutex();
    private static ReentrantLock mutex = new ReentrantLock();
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                mutex.lock();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.unlock();
                }
            });
            thread.start();
        }
    }
}
