package com.juc.cpp.jmm;

public class VolatileTest {
    private static volatile int a;

    public static void main(String[] args) {
        a = 1;
    }
}
