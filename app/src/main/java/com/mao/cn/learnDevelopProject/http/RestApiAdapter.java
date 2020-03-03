// +----------------------------------------------------------------------
// | FileName:   ${file_name}  
// +----------------------------------------------------------------------
// | CreateTime: 15/6/28  @下午7:36
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.http;

import com.google.gson.Gson;
import com.mao.cn.learnDevelopProject.LearnDevelopApplication;
import com.mao.cn.learnDevelopProject.factory.StringConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Create By Xueyong
 */
public class RestApiAdapter {
    private static Retrofit sharedStringInstance = null;
    private static Retrofit timeoutStringInstance = null;
    private static Retrofit rxHttpsStringInstance;
    private static Retrofit rxGsonInstance;
    private static Retrofit commomInstance = null;

    private static OkHttpClient client;
    private static OkHttpClient timeoutClient;


    public static Retrofit getHttpsRxStringInstance() {
        if (client == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
        }

        if (rxHttpsStringInstance == null) {
            rxHttpsStringInstance = new Builder().baseUrl("https://api.douban.com/")
                    .client(client)
                    .addConverterFactory(new StringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return rxHttpsStringInstance;
    }

    public static Retrofit getStringInstance() {
        if (client == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
        }

        if (sharedStringInstance == null) {
            sharedStringInstance = new Builder()
                    .baseUrl(LearnDevelopApplication.serverInfo().getServerHost())
                    .client(client)
                    .addConverterFactory(new StringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return sharedStringInstance;
    }

    public static Retrofit getStringInstanceTimeOut() {
        if (timeoutClient == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            timeoutClient = new OkHttpClient.Builder()
                    .writeTimeout(50, TimeUnit.SECONDS)
                    .readTimeout(50, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();
        }

        if (timeoutStringInstance == null) {
            timeoutStringInstance = new Builder()
                    .baseUrl(LearnDevelopApplication.serverInfo().getServerHost())
                    .client(timeoutClient)
                    .addConverterFactory(new StringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return timeoutStringInstance;
    }


    public static Retrofit getRxGsonInstance() {
        if (client == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
        }

        if (rxGsonInstance == null) {
            rxGsonInstance = new Builder().baseUrl(LearnDevelopApplication.serverInfo().getServerHost())
                    .client(client)
                    .addConverterFactory(new StringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return rxGsonInstance;
    }


    public static Retrofit getBaiduDictionary() {
        if (client == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
        }

        if (sharedStringInstance == null) {
            sharedStringInstance = new Builder()
                    .baseUrl("http://fanyi-api.baidu.com/")
                    .client(client)
                    .addConverterFactory(new StringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return sharedStringInstance;
    }

    public static Retrofit getJinShanDictionary() {
        if (client == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
        }

        if (sharedStringInstance == null) {
            sharedStringInstance = new Builder()
                    .baseUrl("http://dict-co.iciba.com/")
                    .client(client)
                    .addConverterFactory(new StringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return sharedStringInstance;
    }

    public static Retrofit getGirlImage() {
        if (client == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
        }

        if (commomInstance == null) {
            commomInstance = new Builder()
                    .baseUrl("http://gank.io/api/")
                    .client(client)
                    .addConverterFactory(new StringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return commomInstance;
    }


    public static void clean() {
        sharedStringInstance = null;
        rxHttpsStringInstance = null;
        rxGsonInstance = null;
        client = null;
        timeoutStringInstance = null;
        commomInstance = null;
    }

}
