package com.juc.cpp.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        //1、普通的线程锁 功能等同于synchronized
//        Output o = new Output();
//        new Thread() {
//            public void run() {
//                o.output("test");
//            }
//        }.start();
//        new Thread() {
//            public void run() {
//                o.output("lock");
//            }
//        }.start();
        //2、带限制的锁 轮询锁 超时锁 中断锁
//        TryLock tryLock = new TryLock();
//        new Thread(new Runnable() {
//            public void run() {
//                tryLock.tryLock();
//            }
//        },"tryLock_thread1").start();
//        new Thread(new Runnable() {
//            public void run() {
//                tryLock.tryLock();
//            }
//        },"tryLock_thread2").start();
//        TryLockNanos tryLock = new TryLockNanos();
//        new Thread(new Runnable() {
//            public void run() {
//                tryLock.tryLock(10,TimeUnit.NANOSECONDS);
//            }
//        },"tryLockNanos_thread1").start();
//        new Thread(new Runnable() {
//            public void run() {
//                tryLock.tryLock(50,TimeUnit.NANOSECONDS);
//            }
//        },"tryLockNanos_thread2").start();
        TryLockInterruptibly tryLock = new TryLockInterruptibly();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                tryLock.tryLockInterruptibly();
            }
        },"tryLockInterruptibly_thread1");
        thread1.start();
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            System.out.println("main thread was interrupted");
        }
        thread1.interrupt();
    }
}

//class Output {
//    ReentrantLock lock = new ReentrantLock();
//
//    public void output(String name) {
//        lock.lock();
//        try {
//            for(int i=0; i<name.length(); i++) {
//                System.out.println(name.charAt(i));
//            }
//        }catch(Exception e) {
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
//    }
//}

//class TryLock {
//    ReentrantLock lock = new ReentrantLock();
//
//    public void tryLock() {
//        if(lock.tryLock()) {
//            try{
//                System.out.println(Thread.currentThread().getName() + " tryLock get lock");
//                Thread.sleep(1000);
//            }catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//                lock.unlock();
//                System.out.println(Thread.currentThread().getName() + " tryLock release lock");
//            }
//        } else {
//            System.out.println(Thread.currentThread().getName() + " tryLock can not get lock");
//        }
//    }
//}

//class TryLockNanos {
//    ReentrantLock lock = new ReentrantLock();
//
//    public void tryLock(long timeout, TimeUnit unit) {
//            try{
//                if(lock.tryLock(timeout,unit)) {
//                    System.out.println(Thread.currentThread().getName() + " tryLock get lock");
//                } else {
//                    System.out.println(Thread.currentThread().getName() + " tryLock can not get lock");
//                }
//            }catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//                lock.unlock();
//                System.out.println(Thread.currentThread().getName() + " tryLock release lock");
//            }
//    }
//}

class TryLockInterruptibly {
    ReentrantLock lock = new ReentrantLock();

    public void tryLockInterruptibly() {
            try{
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " tryLock get lock");
            }catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted");
                e.printStackTrace();
            }finally {
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " was interrupted");
                    e.printStackTrace();
                }
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " tryLock release lock");
            }
    }
}