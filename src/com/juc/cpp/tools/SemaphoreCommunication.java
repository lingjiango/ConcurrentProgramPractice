package com.juc.cpp.tools;

import java.util.concurrent.Semaphore;

public class SemaphoreCommunication {
    public static void main(String[] args) {
        //2、线程间进行通信
        Semaphore semaphore = new Semaphore(1);
        new SendingThread(semaphore,"SendingThread");
        new ReceivingThread(semaphore,"ReceivingThread");
    }
}
class SendingThread extends Thread {
    Semaphore semaphore;
    String name;

    public SendingThread(Semaphore semaphore,String name) {
        this.semaphore = semaphore;
        this.name = name;
        new Thread(this).start();
    }

    public void run() {
        try {
            semaphore.acquire();
            for (int i = 0; i < 5; i++) {
                System.out.println(name + ":" + i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        semaphore.release();
    }
}

class ReceivingThread extends Thread {
    Semaphore semaphore;
    String name;

    public ReceivingThread(Semaphore semaphore,String name) {
        this.semaphore = semaphore;
        this.name = name;
        new Thread(this).start();
    }

    public void run() {
        try {
            semaphore.acquire();
            for (int i = 0; i < 5; i++) {
                System.out.println(name + ":" + i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        semaphore.release();
    }
}