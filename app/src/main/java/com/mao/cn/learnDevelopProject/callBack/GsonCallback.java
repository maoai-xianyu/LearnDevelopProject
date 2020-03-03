package com.mao.cn.learnDevelopProject.callBack;

import com.mao.cn.learnDevelopProject.converter.RetrofitError;

import retrofit2.Response;

public abstract class GsonCallback<T> extends BaseCallback<T> {
    public GsonCallback() {
    }


    @Override
    public void success(Response<T> response) {
        try {
            this.success(response.body());
        } catch (Exception var5) {
            RetrofitError error = new RetrofitError();
            error.setCode(3);
            error.setMessage(var5.getMessage());
            this.failure(error);
        }

    }

    public abstract void success(T var1);
}