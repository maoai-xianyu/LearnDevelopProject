//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mao.cn.learnDevelopProject.utils.download;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class TaskManager {
    private static TaskManager.ThreadPool poolProxy;
    private static final int maxPoolSize = 5;
    private static final int keepAlive = 30000;

    public TaskManager() {
    }

    public static TaskManager.ThreadPool getPoolProxy() {
        if (poolProxy == null) {
            Class var0 = TaskManager.class;
            synchronized(TaskManager.class) {
                if (poolProxy == null) {
                    int processorCount = Runtime.getRuntime().availableProcessors();
                    int maxAvailable = Math.max(processorCount * 3, 5);
                    poolProxy = new TaskManager.ThreadPool(processorCount, maxAvailable, 30000);
                }
            }
        }

        return poolProxy;
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private final ThreadGroup group = Thread.currentThread().getThreadGroup();

        DefaultThreadFactory() {
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(this.group, r, Thread.currentThread().getName(), 0L);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }

            t.setPriority(5);
            return t;
        }
    }

    public static class ThreadPool {
        private ThreadPoolExecutor tpExecutor;
        private int corePoolSize;
        private int maximumPoolSize;
        private int keepAliveTime;

        public ThreadPool(int corePoolSize, int maximumPoolSize, int keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        public void execute(Runnable runnable) {
            if (runnable != null) {
                if (this.tpExecutor == null || this.tpExecutor.isShutdown()) {
                    Class var2 = TaskManager.class;
                    synchronized(TaskManager.class) {
                        if (this.tpExecutor == null || this.tpExecutor.isShutdown()) {
                            this.tpExecutor = this.createExecutor();
                            this.tpExecutor.allowCoreThreadTimeOut(false);
                        }
                    }
                }

                this.tpExecutor.execute(runnable);
            }
        }

        public void shutdown() {
            if (this.tpExecutor != null && !this.tpExecutor.isShutdown()) {
                Class var1 = TaskManager.class;
                synchronized(TaskManager.class) {
                    if (this.tpExecutor != null && !this.tpExecutor.isShutdown()) {
                        this.tpExecutor.shutdownNow();
                    }
                }
            }

        }

        private ThreadPoolExecutor createExecutor() {
            return new ThreadPoolExecutor(this.corePoolSize, this.maximumPoolSize, (long)this.keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new TaskManager.DefaultThreadFactory(), new AbortPolicy());
        }
    }
}
