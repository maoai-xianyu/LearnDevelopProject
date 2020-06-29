package com.mao.cn.learnDevelopProject.useDesign.d_05_templatePattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangkun
 * @time 2020-05-19 17:33
 * @Description 开线程下载100张图片
 */
public class ThreadPoolTest {


   /* private static final int CORE_POOL_SIZE = 1;
    private static final int MAXIMUM_POOL_SIZE = 20;
    private static final int BACKUP_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_SECONDS = 3;*/


    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // We want at least 2 threads and at most 4 threads in the core pool,
    // preferring to have 1 less than the CPU count to avoid saturating
    // the CPU with background work
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;

    static ThreadPoolExecutor threadPoolExecutor;

    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(128);


    static {
        threadPoolExecutor = new ThreadPoolExecutor(
                4,//CORE_POOL_SIZE, // 核心线程数，就是线程池里面的核心线程数量
                10,//MAXIMUM_POOL_SIZE, // 最大线程数，线程池中最大线程数
                60,//KEEP_ALIVE_SECONDS, // 线程存活的时间，没事干的时候的空闲存活时间，超过这个时间线程就会被销毁
                TimeUnit.SECONDS, // 线程存活的时间的单位
                sPoolWorkQueue, // 线程的队列
                new ThreadFactory() {  // 线程创建工厂，如果线程池需要创建线程，就会调用 newThread 来创建
                    @Override
                    public Thread newThread(Runnable r) {

                        Thread thread = new Thread(r, "自己的线程的名字");
                        thread.setDaemon(false); // 不是守护线程

                        return thread;
                    }
                });
    }


    public static void main(String[] args) {

        downloadImagePool();
    }

    public static void downloadImagePool() {
        for (int i = 0; i < 100; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("下载图片成功" + Thread.currentThread().getName());

                }
            };
            // 加入线程队列，寻找合适的时机去执行
            threadPoolExecutor.execute(runnable);
        }
    }


    /**
     * 开100个线程
     */
    public static void downloadImage() {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("下载图片成功");
                }
            }).start();
        }
    }


}
