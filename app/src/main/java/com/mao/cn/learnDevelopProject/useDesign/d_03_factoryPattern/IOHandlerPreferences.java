package com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern;

/**
 * @author zhangkun
 * @time 2020-05-18 15:09
 * @Description 磁盘缓存
 */
public class IOHandlerPreferences implements IOHandler {

    @Override
    public void save(String key, String value) {
        PreferenceUtils.getInstance().saveString(key, value);
    }

    @Override
    public void save(String key, double value) {

    }

    @Override
    public void save(String key, int value) {

    }

    @Override
    public void save(String key, long value) {

    }

    @Override
    public void save(String key, float value) {

    }

    @Override
    public void save(String key, boolean value) {

    }

    @Override
    public void save(String key, Object value) {

    }

    @Override
    public String getString(String key, String defaultValue) {

        return PreferenceUtils.getInstance().getString(key, defaultValue);
    }

    @Override
    public String getString(String key) {
        return PreferenceUtils.getInstance().getString(key);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return 0;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return 0;
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return 0;
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return 0;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return false;
    }

    @Override
    public Object getObject(String key, Object defaultValue) {
        return null;
    }
}
