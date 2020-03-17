package com.mao.cn.learnDevelopProject.designPattern.Pattern22_oralPattern.protomode;

/**
 * @author zhangkun
 * @time 2020-03-17 22:45
 */
public class ThreadTest {
    public static void main(String[] args) {

        ThreadTest threadTest = new ThreadTest();

        MyRunnable myRunnable = threadTest.new MyRunnable();

        for (int i = 0; i < 5; i++) {
            new Thread(myRunnable).start();
        }
    }

    public int i = 0;


    class MyRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                //锁住的是同一对象
                synchronized (this) {
                    if (i >= 1000) {
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + ":count:" + (++i));
                    //测试时，线程更容易切换
                    //Thread.yield();
                }
            }
        }
    }
}
