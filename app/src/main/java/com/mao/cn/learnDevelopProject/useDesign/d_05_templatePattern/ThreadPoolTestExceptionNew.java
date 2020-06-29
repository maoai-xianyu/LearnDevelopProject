package com.mao.cn.learnDevelopProject.useDesign.d_05_templatePattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangkun
 * @time 2020-05-19 17:33
 * @Description 开线程下载100张图片
 */
public class ThreadPoolTestExceptionNew {



    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;

    static ThreadPoolExecutor threadPoolExecutor;

    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new PriorityBlockingQueue<Runnable>(4);


    // Queue 的参数
    // BlockingQueue: 先进先出的一个队列 FIFO
    // SynchronousQueue: 线程安全的队列，它里面是没有固定的缓存的（OKHttp所使用的）
    // PriorityBlockingQueue: 无序的可以根据优先级进行排序 ，指定的对象要实现 Comparable 作比较 


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

                        return new Thread(r);
                    }
                });
    }


    public static void main(String[] args) {

        downloadImagePool();
    }

    public static void downloadImagePool() {
        for (int i = 0; i < 20; i++) {
            Request runnable = new Request();
            // 加入线程队列，寻找合适的时机去执行
            threadPoolExecutor.execute(runnable);
        }
    }

}
