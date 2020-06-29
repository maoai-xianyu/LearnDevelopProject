package com.mao.cn.learnDevelopProject.useDesign.d_01_SinglePattern;

/**
 * @author zhangkun
 * @time 2020-05-15 00:04
 * @Description 单例设计模式 - 懒汉式 - 多线程并发 双重检测 (同步锁DCL)
 */
public class Singleton4 {

    // 使用的时候才会去new对象，可能更加高效
    // 会有问题？多线程并发的问题，如果多个线程调用会有多个实例 如何解决
    // 添加 volatile 的用处是什么？
    // 1. 防止重排序
    // 2. 线程可见性-某一个线程改了公用对象（变量），短时间内另一个线程可能是不可见的，因为每一个线程都有自己的缓存区（线程工作区）

    private static volatile Singleton4 mInstance;

    private Singleton4() {

    }

    // Singleton4 mInstance = new Singleton4();
    // 1. 开辟一块空间（内存）
    // 2. 初始化对象
    // 3. 给变量赋值（指向内存地址）
    // 但是 2 和 3 在java多线程顺序是不固定的  volatile 为了防止 2和3互换位置
    // 1. 开辟一块空间（内存）
    // 2. 给变量赋值（指向内存地址）
    // 3. 初始化对象

    // 同步锁，解决了线程安全的问题。但是会出现效率的问题？
    // 既保证线程安全同时效率比较高
    // 这种方式还是会有问题的？ 用 volatile 解决
    public static Singleton4 getInstance() {
        if (mInstance == null) {
            synchronized (Singleton4.class) {
                if (mInstance == null) {
                    mInstance = new Singleton4();
                }
            }
        }
        return mInstance;
    }
}
