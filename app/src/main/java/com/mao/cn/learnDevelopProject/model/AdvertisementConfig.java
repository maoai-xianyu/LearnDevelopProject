package com.mao.cn.learnDevelopProject.model;

// +----------------------------------------------------------------------
// | CreateTime: 16/10/23 
// +----------------------------------------------------------------------
// | Author:     zhangkun
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AdvertisementConfig implements Serializable {

    @Expose
    private String update_count;
    @Expose
    private String update_time;
    @Expose
    private String url;

    public String getUpdate_count() {
        return update_count;
    }

    public void setUpdate_count(String update_count) {
        this.update_count = update_count;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AdvertisementConfig{" +
                "update_count='" + update_count + '\'' +
                ", update_time='" + update_time + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
