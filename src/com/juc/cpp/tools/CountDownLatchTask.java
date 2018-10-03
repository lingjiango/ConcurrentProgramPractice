package com.juc.cpp.tools;

import java.util.concurrent.CountDownLatch;


/*
模拟工作中的一个需求场景：
用户会选择多个算法来计算费用，最后会将所有算法计算出的费用做一个加权求平均数，这个平均数是最终的费用。
每个算法的复杂度都不一样，打算每个线程负责一个算法的实现，所有的线程执行完成，最后再求平均数。
1、为每个算法创建一个线程，每个线程负责一个算法的实现
2、通过CountDownLatch来控制所有算法线程的同步
3、全部计算完成后再求平均数
 */
public class CountDownLatchTask {

    public static void main(String[] args) {
        CountDownLatchTask countDownLatchTask = new CountDownLatchTask();
        countDownLatchTask.startThreads(5);
    }
    //根据线程数和选择的算法 调度算法对应的实现
    private void startThreads(int threadNumber) {
        CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
        for (int i = 0; i < threadNumber; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程算法实现：" + Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
            System.out.println("加权求平均数");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
