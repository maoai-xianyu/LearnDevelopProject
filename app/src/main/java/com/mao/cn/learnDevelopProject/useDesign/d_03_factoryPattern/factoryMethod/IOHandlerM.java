package com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factoryMethod;

/**
 * @author zhangkun
 * @time 2020-05-18 15:04
 * @Description 数据存储的一些规范
 */
public interface IOHandlerM {


    // 存数据

    void save(String key, String value);

    void save(String key, double value);

    void save(String key, int value);

    void save(String key, long value);

    void save(String key, float value);

    void save(String key, boolean value);

    void save(String key, Object value);


    //  获取数据
    String getString(String key, String defaultValue);

    String getString(String key);

    double getDouble(String key, double defaultValue);

    int getInt(String key, int defaultValue);

    long getLong(String key, long defaultValue);

    float getFloat(String key, float defaultValue);

    boolean getBoolean(String key, boolean defaultValue);

    Object getObject(String key, Object defaultValue);
}
