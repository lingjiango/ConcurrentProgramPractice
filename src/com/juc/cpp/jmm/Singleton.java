package com.juc.cpp.jmm;

public class Singleton {
    private static volatile  Singleton instance;
    private Singleton() {};
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
