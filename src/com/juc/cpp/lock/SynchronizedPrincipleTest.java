package com.juc.cpp.lock;
//javac
//javap -c
//javap -verbose
public class SynchronizedPrincipleTest {
    public synchronized void f1() {
        System.out.println("synchronized void f1()");
    }

    public void f2() {
        synchronized(this) {
            System.out.println("synchronized(this)");
        }
    }

    public static void main(String[] args) {
        SynchronizedPrincipleTest test = new SynchronizedPrincipleTest();
        test.f1();
        test.f2();
    }
}
