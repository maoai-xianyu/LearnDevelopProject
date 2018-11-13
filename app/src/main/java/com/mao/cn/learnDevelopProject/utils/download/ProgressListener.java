package com.mao.cn.learnDevelopProject.utils.download;

public interface ProgressListener {
    void update(long var1, long var3);

    void success(DLTask var1);

    void fail(DLTask var1);
}
