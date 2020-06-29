package com.mao.cn.learnDevelopProject.useDesign.d_01_SinglePattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangkun
 * @time 2020-05-15 10:41
 * @Description 单例设计模式 - 容器管理 - SystemServiceRegistry 获取系统的服务
 */
public class Singleton7 {

    private static Map<String, Object> mSingleMap = new HashMap<>();

    static {
        mSingleMap.put("activity_manager", new Singleton7());
    }

    private Singleton7() {

    }

    public static Object getService(String serviceName) {
        return mSingleMap.get(serviceName);
    }
}
