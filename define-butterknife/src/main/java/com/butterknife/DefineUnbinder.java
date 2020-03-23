package com.butterknife;

import androidx.annotation.UiThread;

/**
 * @author zhangkun
 * @time 2020-03-23 18:31
 */
public interface DefineUnbinder {
    @UiThread
    void unbind();

    DefineUnbinder EMPTY = new DefineUnbinder() {
        @Override
        public void unbind() {

        }
    };
}
