package com.mao.cn.learnDevelopProject.useDesign.d_01_SinglePattern;

/**
 * @author zhangkun
 * @time 2020-05-15 10:14
 * @Description
 */
public class VolatileTest {

    public static void main(String[] args) {
        ThreadRun threadRun = new ThreadRun();

        new Thread(threadRun).start();

        while (true) {
            if (threadRun.isFlag()) {
                System.out.println("--------跳出循环------");
                break;
            }
        }

        // 没有个 flag 设置 Volatile 结果一直是  flag   true 不会执行 --------跳出循环------
    }

    static class ThreadRun implements Runnable {
        private volatile boolean flag = false;

        @Override
        public void run() {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println(" flag " + isFlag());
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}


