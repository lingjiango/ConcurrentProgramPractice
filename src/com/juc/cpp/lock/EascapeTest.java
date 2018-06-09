package com.juc.cpp.lock;

public class EascapeTest {
    private static String concatString(String s1,String s2) {
        return s1 + s2;
    }

    public static void main(String[] args) {
        EascapeTest eascapeTest = new EascapeTest();
        eascapeTest.concatString("a","b");
    }
}
