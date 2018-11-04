package com.juc.cpp.tools;

import java.util.concurrent.*;

public class FutureTask4Executor {
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int i = 0;
                for ( ;i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "_"
                            + i);
                    Thread.sleep(500);
                }
                return i;
            }
        }) {
            @Override
            protected void done() {
                try {
                    System.out.println("futureTask.done():" + get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        // 创建线程池（使用了预定义的配置）
        ExecutorService executor = Executors.newCachedThreadPool();
//        executor.execute(futureTask);
        executor.submit(futureTask);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        try {
            // 阻塞，等待异步任务执行完毕-获取异步任务的返回值
            System.out.println("futureTask.get():" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
