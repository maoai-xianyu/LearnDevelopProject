//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mao.cn.learnDevelopProject.callBack;

import com.mao.cn.learnDevelopProject.converter.RetrofitError;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseCallback<T> implements Callback<T> {
    protected Response response;

    public BaseCallback() {
    }

    public void onResponse(Call<T> call, Response<T> response) {
        this.response = response;
        if (response != null) {
            if (response.isSuccessful()) {
                this.success(response);
            } else {
                RetrofitError error = new RetrofitError();
                error.setCode(response.code());
                error.setMessage(response.message());
                ResponseBody errorBody = response.errorBody();
                if (errorBody != null) {
                    String body = "";

                    try {
                        body = errorBody.string();
                    } catch (IOException var7) {
                    }

                    error.setBody(body);
                }

                this.failure(error);
            }
        } else {
            this.onFailure(call, (Throwable)null);
        }

    }

    public void onFailure(Call<T> call, Throwable e) {
        HandlerError error = new HandlerError(e);
        this.failure(error.execute());
    }

    public String getHeader(String key) {
        return this.response.headers().get(key);
    }

    public String getETag() {
        return this.response.headers().get("ETag");
    }

    public abstract void failure(RetrofitError var1);

    public abstract void success(Response<T> var1);
}
