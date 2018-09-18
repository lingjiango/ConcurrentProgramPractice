package com.juc.cpp.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreConnect {
    public static void main(String[] args) throws Exception {
        //3、模拟连接池数量限制
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 200; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
class Connection {
    private static Connection instance = new Connection();
    private Semaphore semaphores = new Semaphore(10,true);
    private int connections = 0;

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            semaphores.acquire();
            doConnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphores.release();
        }
    }

    private void doConnect() {
        synchronized (this) {
            connections ++;
            System.out.println("current get connections is : " + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections --;
            System.out.println("after release current  connections is : " + connections);
        }
    }
}